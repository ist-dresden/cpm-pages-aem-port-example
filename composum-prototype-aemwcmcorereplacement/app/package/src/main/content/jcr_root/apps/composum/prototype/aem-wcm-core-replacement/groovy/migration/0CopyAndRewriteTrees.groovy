// Copy a number of subtrees from the imported AEM site to a new location while rewriting internal references.
// We overwrite the existing subtrees if they exist, to be sure we apply the latest migrations.


import org.apache.commons.lang3.tuple.Pair
import org.apache.sling.api.resource.Resource

// a list of subtrees to copy including their destination
List<Pair<String, String>> subtrees = new ArrayList<Pair<String, String>>();
subtrees.add(Pair.of("/content/wknd/language-masters/en", "/content/wknd-cpm/language-masters/en"));
// subtrees.add(Pair.of("/content/experience-fragments/wknd/language-masters/en", "/content/wknd-cpm/experience-fragments/language-masters/en")); // doubtful location
// subtrees.add(Pair.of("/content/dam/wknd/en", "/content/wknd-cpm/assets/en"));

// Create the parents as sling:OrderedFolder nodes if they aren't already there.
void createParents(String path) {
    path = path.substring(0, path.lastIndexOf("/"));
    String[] parts = path.split("/");
    Resource parent = resourceResolver.getResource("/");
    String currentPath = "/";
    for (String part : parts) {
        if (part.length() > 0) {
            Resource current = resourceResolver.getResource(currentPath + part);
            if (current == null) {
                current = resourceResolver.create(parent, part, Collections.singletonMap("jcr:primaryType", "sling:OrderedFolder"));
                println("Created " + current.getPath());
            }
            currentPath += part + "/";
            parent = current;
        }
    }
    println("Parent " + path + " exists now.");
}

// copies the resources of a subtree recursively from src to dest
// while rewriting internal references: any String property that starts with one of the subtrees left
// is rewritten to start with the corresponding subtree right. All other attributes are copied as is.
void copyAndRewrite(String src, String dest, List<Pair<String, String>> subtrees) {
    copyAndRewriteOneNode(src, dest, subtrees);
    resourceResolver.findResources("/jcr:root" + src + "//*", "xpath").each { Resource r ->
        String destPath = dest + r.getPath().substring(src.length());
        copyAndRewriteOneNode(r.getPath(), destPath, subtrees);
    }
}

void copyAndRewriteOneNode(String src, String dest, List<Pair<String, String>> subtrees) {
    Resource srcResource = resourceResolver.getResource(src);
    if (srcResource == null) {
        throw new Exception("Source " + src + " does not exist.");
    }
    Resource destParent = resourceResolver.getResource(dest.substring(0, dest.lastIndexOf("/")));
    if (destParent == null) {
        throw new Exception("Destination parent of " + dest + " does not exist.");
    }
    Map<String, Object> destProps = new HashMap<String, Object>();
    // put all properties from r.getValueMap() into destProps while rewriting internal references
    srcResource.getValueMap().entrySet().each { Map.Entry<String, Object> entry ->
        // ignore these keys: jcr:uuid, jcr:baseVersion, jcr:predecessors, jcr:versionHistory, jcr:isCheckedOut
        if (entry.key == "jcr:uuid" || entry.key == "jcr:baseVersion" || entry.key == "jcr:predecessors"
                || entry.key == "jcr:versionHistory" || entry.key == "jcr:isCheckedOut") {
            return;
        }
        if (entry.value instanceof String) {
            String value = entry.value;
            subtrees.each { Pair<String, String> subtree ->
                if (value.startsWith(subtree.left)) {
                    value = subtree.right + value.substring(subtree.left.length());
                }
            }
            destProps.put(entry.key, value);
        } else {
            destProps.put(entry.key, entry.value);
        }
    }
    resourceResolver.create(destParent, srcResource.getName(), destProps);
    // println("Created " + dest);
}


try {
    long start = System.currentTimeMillis();
    // start from scratch: delete the destination subtrees if they exist
    println("Deleting destination subtrees...");
    subtrees.each { Pair<String, String> subtree ->
        Resource dest = resourceResolver.getResource(subtree.right);
        if (dest != null) {
            resourceResolver.delete(dest);
            println("Deleted " + dest.getPath());
        }
    }
    // recursively create parent nodes of the subtrees if not existing
    println("Creating parent nodes...");
    subtrees.each { Pair<String, String> subtree ->
        createParents(subtree.right);
    }
    // copy the subtrees while rewriting internal references
    println("Copying subtrees...");
    subtrees.each { Pair<String, String> subtree ->
        copyAndRewrite(subtree.left, subtree.right, subtrees);
    }
    // resourceResolver.revert();
    resourceResolver.commit();
    println("Copy done in " + (System.currentTimeMillis() - start) + " ms.");
} catch (Throwable e) {
    println();
    println();
    println();
    println("Migration failed because of " + e);
    println(e.getCause());
    resourceResolver.revert();
    throw e;
}

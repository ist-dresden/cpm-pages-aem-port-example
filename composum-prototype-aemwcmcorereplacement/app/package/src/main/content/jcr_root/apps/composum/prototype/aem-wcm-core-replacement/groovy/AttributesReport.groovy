import org.apache.commons.lang3.tuple.Pair
import org.apache.sling.api.resource.Resource

void checkPath(String path) {
    checkQuery("/jcr:root" + path + "//*")
}

void checkQuery(String query) {
    Map<Pair<String, String>, Integer> nodecount = new TreeMap();
    Map<Pair<String, String>, Map<String, Integer>> attrcount = new TreeMap();
    Map<Pair<String, String>, Map<Pair<String, String>, Integer>> childcount = new TreeMap();

    resourceResolver.findResources(query, "xpath").each { Resource r ->
        String primtype = r.getValueMap().get("jcr:primaryType")
        String restype = r.getValueMap().get("sling:resourceType")
        Pair<String, String> key = Pair.of(primtype, restype)

        nodecount.put(key, nodecount.getOrDefault(key, 0) + 1)
        attrcount.putIfAbsent(key, new TreeMap())
        childcount.putIfAbsent(key, new TreeMap())

        r.getValueMap().entrySet().each { Map.Entry<String, Object> entry ->
            attrcount.get(key).put(entry.key, attrcount.get(key).getOrDefault(entry.key, 0) + 1)
        }
        r.getChildren().each { Resource child ->
            Pair<String, String> childkey = Pair.of(child.getValueMap().get("jcr:primaryType"), child.getValueMap().get("sling:resourceType"))
            childcount.get(key).put(childkey, childcount.get(key).getOrDefault(childkey, 0) + 1)
        }
    }

    def excludePattern = ~/(jcr:primaryType|sling:resourceType|cq:lastRolledout(By)?|jcr:lastModified(By)?|jcr:created(By)?)/

    nodecount.entrySet().toList().sort { a, b -> b.value <=> a.value }.each { Map.Entry<Pair<String, String>, Integer> entry ->
        println(entry.key.left + " " + entry.key.right + ": (" + entry.value + " times)")

        attrcount.getOrDefault(entry.key, Collections.emptyMap()).entrySet().toList().sort { a, b -> b.value <=> a.value }.each { Map.Entry<String, Integer> attr ->
            if (!attr.key.matches(excludePattern)) {
                println("- " + attr.key + " (" + Math.round(attr.value * 100.0 / entry.value) + "%)")
            }
        }

        childcount.getOrDefault(entry.key, Collections.emptyMap()).entrySet().toList().sort { a, b -> b.value <=> a.value }.each { Map.Entry<Pair<String, String>, Integer> child ->
            println("+ " + child.key.left + " " + child.key.right + " (" + Math.round(child.value * 100.0 / entry.value) + "%)")
        }

        println()
    }
}

checkPath("/content/wknd")

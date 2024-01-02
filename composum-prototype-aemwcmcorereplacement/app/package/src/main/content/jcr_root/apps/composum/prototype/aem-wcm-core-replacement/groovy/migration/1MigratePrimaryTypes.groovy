// performs basic migration of the jcr:primaryType properties


import org.apache.sling.api.resource.ModifiableValueMap
import org.apache.sling.api.resource.Resource

void changePrimaryTypes(String path, String originalPrimaryType, String newPrimaryType) {
    resourceResolver.findResources(
            "/jcr:root" + path + "//*",
            "xpath"
    ).each { Resource r ->
        def primaryType = r.getValueMap().get("jcr:primaryType")
        // if primary type is cq:PageContent we need to add mixin mix:versionable first because cpp:PageContent is versionable
        // otherwise we get an exception when trying to change the primary type
        if (primaryType == "cq:PageContent") {
            r.adaptTo(javax.jcr.Node.class).addMixin("mix:versionable");
        }
        if (primaryType == originalPrimaryType) {
            r.adaptTo(ModifiableValueMap.class).put("jcr:primaryType", newPrimaryType);
            println("Changed " + r.getPath() + " from " + originalPrimaryType + " to " + newPrimaryType);
        }
        if (primaryType == "cq:PageContent") {
            r.adaptTo(javax.jcr.Node.class).removeMixin("mix:versionable"); // that's implicite now.
        }
    }
}

try {
    changePrimaryTypes("/content/wknd-cpm", "cq:Page", "cpp:Page");
    changePrimaryTypes("/content/wknd-cpm", "cq:PageContent", "cpp:PageContent");
    resourceResolver.getResource("/content/wknd").adaptTo(ModifiableValueMap.class).put("jcr:primaryType", "cpp:Page");
    resourceResolver.commit();
    println("Migration done.")
} catch (Exception e) {
    println("Migration failed because of " + e);
    println(e.getCause());
    resourceResolver.revert();
    throw e;
}

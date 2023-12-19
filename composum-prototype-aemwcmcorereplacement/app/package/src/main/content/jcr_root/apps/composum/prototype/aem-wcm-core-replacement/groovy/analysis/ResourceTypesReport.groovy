import org.apache.sling.api.resource.Resource;

void checkPath(String path) {
    println("Path: " + path)

    Map<String, Integer> primtypes = new TreeMap();
    Map<String, Integer> mixins = new TreeMap();
    Map<String, Integer> restypes = new TreeMap();

    resourceResolver.findResources(
            "/jcr:root" + path + "//*",
            "xpath"
    ).each { Resource r ->
        String primtype = r.getValueMap().get("jcr:primaryType")
        primtypes.put(primtype, primtypes.getOrDefault(primtype, 0) + 1)

        String[] mix = r.getValueMap().get("jcr:mixinTypes", new String[0])
        mix.each {m ->
            mixins.put(m, mixins.getOrDefault(m, 0) + 1)
        }

        String restype = r.getValueMap().get("sling:resourceType", "")
        restypes.put(restype, restypes.getOrDefault(restype, 0) + 1)
    }

    println "\n\nPrimtypes:"
    primtypes.entrySet().each {entry ->
        println entry.getKey() + "\t" + entry.getValue()
    }

    println "\n\nMixins:"
    mixins.entrySet().each {entry ->
        println entry.getKey() + "\t" + entry.getValue()
    }

    println "\n\nsling:resourceType sorted by key:"
    restypes.entrySet().each {entry ->
        println entry.getKey() + "\t" + entry.getValue()
    }

    println "\n\nsling:resourceType sorted by count:"
    restypes.entrySet().sort { a, b -> b.getValue() <=> a.getValue() }.each {entry ->
        println entry.getKey() + "\t" + entry.getValue()
    }
}

// checkPath("/content/wknd")
// checkPath("/content/dam/wknd")
checkPath("/content/experience-fragments/wknd")

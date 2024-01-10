// Looks up the composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationService and calls the migrate() method for all trees we want to migrate.


import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationService

def pathList = [
        "/content/wknd-cpm"
];
def siteMarkerXpath = "//*[@thisIsASiteRoot]";

// ================== end of configuration ==================

//println "meta: " + getMetaClass()
//println "log: " + log
//println "binding: " + binding
//binding.variables.each({ entry ->
//    println "  " + entry.key + " = " + entry.value
//});

try {
    AemWcmCoreMigrationService service = bundleContext.getService(bundleContext.getServiceReference(
            AemWcmCoreMigrationService.class.getName()))

    println("Migrating sites");
    pathList.each { String path ->
        println("Migrating " + path);
        service.migrateSiteRoots(resourceResolver.getResource(path), out);
    }

    println("Migrating " + pathList.size() + " trees...");
    pathList.each { path ->
        def resource = resourceResolver.getResource(path);
        service.migrateResourceTree(resource, out);
    }

    println("Starting commit.")
    // resourceResolver.revert();
    resourceResolver.commit();
    println("Migration done.")
} catch (Throwable e) {
    println("Migration failed because of " + e);
    println(e.getCause());
    resourceResolver.revert();
    throw e;
}

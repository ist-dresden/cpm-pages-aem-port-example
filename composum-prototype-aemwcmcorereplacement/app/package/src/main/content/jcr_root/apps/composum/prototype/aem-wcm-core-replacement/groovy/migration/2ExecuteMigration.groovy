// Looks up the composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationService and calls the migrate() method for all trees we want to migrate.

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationService

//println "meta: " + getMetaClass()
//println "log: " + log
//println "binding: " + binding
//binding.variables.each({ entry ->
//    println "  " + entry.key + " = " + entry.value
//});

try {
    AemWcmCoreMigrationService service = bundleContext.getService(bundleContext.getServiceReference(
            AemWcmCoreMigrationService.class.getName()))

    def pathList = [
        "/content/wknd-cpm"
    ];
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

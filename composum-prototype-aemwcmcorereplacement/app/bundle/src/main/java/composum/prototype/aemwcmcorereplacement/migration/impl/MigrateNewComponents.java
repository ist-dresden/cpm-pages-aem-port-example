package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates components that have no Composum equivalent so that we just have to rewrite the resource type.
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateNewComponents extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateNewComponents.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        if (replaceResourceType(resource, "wknd/components/separator",
                "composum/prototype/aem-wcm-core-replacement/components/separator")) {
            LOG.debug("MigrateNewComponents.migrate({} : {})", resource.getResourceType(), resource.getPath());
            log.println("MigrateNewComponents.migrate(" + resource.getResourceType() + " : " + resource.getPath() + ")");
            log.flush();
            migrateCommonAttributes(resource, log);
            return true;
        }
        return false;
    }

}

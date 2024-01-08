package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates wknd/components/xfpage -> composum/pages/components/page/home .
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateExperienceFragmentPage extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateExperienceFragmentPage.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        if (replaceResourceType(resource, "wknd/components/xfpage",
                "composum/pages/components/page/home")) {
            LOG.debug("MigrateExperienceFragmentPage.migrate({})", resource.getPath());
            log.println("MigrateExperienceFragmentPage.migrate(" + resource.getPath() + ")");
            log.flush();
            return true;
        }
        return false;
    }
}

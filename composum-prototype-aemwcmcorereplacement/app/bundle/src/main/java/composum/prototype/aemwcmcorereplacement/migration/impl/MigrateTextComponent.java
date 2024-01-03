package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

@Component
public class MigrateTextComponent implements AemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateTextComponent.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        LOG.debug("MigrateTextComponent.migrate({})", resource.getPath());
        log.println("MigrateTextComponent.migrate(" + resource.getPath() + ")");
        log.flush();
        // TODO implement
        return false;
    }
}

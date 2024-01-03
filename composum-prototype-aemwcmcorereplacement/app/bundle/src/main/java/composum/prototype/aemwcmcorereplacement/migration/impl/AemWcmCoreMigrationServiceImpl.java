package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;
import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationService;

/**
 * Collects all {@link AemWcmCoreMigrationMethod}s and applies them to the given resource until one says it's been migrated.
 * Collects the methods via OSGi service injection.
 */
@Component(service = AemWcmCoreMigrationService.class)
public class AemWcmCoreMigrationServiceImpl implements AemWcmCoreMigrationService {

    private static final Logger LOG = LoggerFactory.getLogger(AemWcmCoreMigrationServiceImpl.class);

    @Reference(service = AemWcmCoreMigrationMethod.class, cardinality = ReferenceCardinality.MULTIPLE)
    private volatile List<AemWcmCoreMigrationMethod> methods;

    @Override
    public void migrateSingleResource(@Nonnull Resource resource, @Nonnull PrintWriter log) {
        for (AemWcmCoreMigrationMethod method : methods) {
            if (method.migrate(resource, log)) {
                LOG.debug("Migrated {} with {}", resource.getPath(), method);
                return;
            }
        }
        LOG.debug("No migration method found for {}", resource.getPath());
    }

}

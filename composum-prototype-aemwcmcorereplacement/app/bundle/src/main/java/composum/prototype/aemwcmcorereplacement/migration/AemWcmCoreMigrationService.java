package composum.prototype.aemwcmcorereplacement.migration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Nonnull;

import org.apache.sling.api.resource.Resource;

public interface AemWcmCoreMigrationService {

    /**
     * Migrates a resource from the AEM WKND way to the Composum way. This just considers this resource - it assumes the children are already migrated.
     */
    void migrateSingleResource(@Nonnull Resource resource, @Nonnull PrintWriter log) throws IOException;

    /**
     * Migrates a resource from the AEM WKND way to the Composum way. This considers this resource and all its children.
     */
    default void migrateResourceTree(@Nonnull Resource resource, @Nonnull PrintWriter log) throws IOException {
        for (Resource child : resource.getChildren()) {
            migrateResourceTree(child, log);
        }
        migrateSingleResource(resource, log);
    }

}

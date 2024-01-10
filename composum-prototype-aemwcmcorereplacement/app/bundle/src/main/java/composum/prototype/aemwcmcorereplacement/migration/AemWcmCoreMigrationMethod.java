package composum.prototype.aemwcmcorereplacement.migration;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;

/**
 * A single migration method that applies to a certain resource type etc. - if it doesn't feel responsible for the given resource it just does nothing.
 */
public interface AemWcmCoreMigrationMethod {

    /**
     * Migrates a resource from the AEM WKND way to the Composum way. This just considers this resource - it assumes the children are already migrated.
     *
     * @return true if this method migrated the resource, false if it didn't feel responsible for the given resource.
     */
    default boolean migrate(Resource resource, PrintWriter log) throws IOException {
        return false;
    }

    /**
     * Migrates a site root marked with {@value AemWcmCoreMigrationService#PROP_SITEROOT_MARKER}.
     *
     * @return true if this method migrated the resource, false if it didn't feel responsible.
     */
    default boolean migrateSiteroot(Resource resource, PrintWriter log) {
        return false;
    }

}

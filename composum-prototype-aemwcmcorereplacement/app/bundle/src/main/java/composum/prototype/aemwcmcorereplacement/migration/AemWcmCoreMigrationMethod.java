package composum.prototype.aemwcmcorereplacement.migration;

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
    boolean migrate(Resource resource, PrintWriter log);

}

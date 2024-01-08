package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates wknd/components/experiencefragment -> composum/pages/components/element/reference .
 * <p>Composum component currently has attribute contentReference .</p>
 * <p>AEM has attributes:</p>
 * <ul>
 *     <li>./fragmentVariationPath - defines the path to the experience fragment variation to be rendered.</li>
 *     <li>./id - defines the component HTML ID attribute.</li>
 * </ul>
 *
 * @see "https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/title/v3/title"
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateExperienceFragment extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateExperienceFragment.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        if (replaceResourceType(resource, "wknd/components/experiencefragment",
                "composum/prototype/aem-wcm-core-replacement/components/experiencefragment")) {
            LOG.debug("MigrateExperienceFragment.migrate({})", resource.getPath());
            log.println("MigrateExperienceFragment.migrate(" + resource.getPath() + ")");
            log.flush();
            migrateCommonAttributes(resource, log);
            String fragmentVariationPath = resource.getValueMap().get("fragmentVariationPath", "");
            String contentReferencePath = fragmentVariationPath + "/jcr:content/root";
            resource.adaptTo(org.apache.sling.api.resource.ModifiableValueMap.class).put("contentReference", contentReferencePath);
            if (resource.getResourceResolver().getResource(contentReferencePath) == null) {
                log.println("ERROR: MigrateExperienceFragment.migrate(" + resource.getPath() + ") contentReferencePath " + contentReferencePath + " does not exist!");
                log.flush();
            }
            return true;
        }
        return false;
    }
}

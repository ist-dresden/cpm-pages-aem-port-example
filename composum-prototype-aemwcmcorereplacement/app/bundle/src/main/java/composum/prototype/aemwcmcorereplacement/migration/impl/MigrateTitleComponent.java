package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates wknd/components/title -> composum/pages/components/element/title  .
 * <p>Composum component currently has attributes title, subtitle, image.</p>
 * <p>AEM has attributes:
 * <ul>
 *     <li>./jcr:title - will store the text of the title to be rendered</li>
 *     <li>./type - will store the HTML heading element type which will be used for rendering; if no value is defined, the component will fallback to the value defined by the component's policy</li>
 *      <li>./linkURL - will allow definition of a content page path, external URL or page anchor for linking the title.</li>
 *      <li>./id - defines the component HTML ID attribute.</li>
 *      <li>./linkAccessibilityLabel - defines an accessibility label for the the title's link.</li>
 *      <li>./linkTitleAttribute - defines a title attribute for the the title's link.</li>
 * </ul>
 * </p>
 *
 * @see "https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/title/v3/title"
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateTitleComponent extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateTitleComponent.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        if (replaceResourceType(resource, "wknd/components/title",
                "composum/prototype/aem-wcm-core-replacement/components/title")) {
            LOG.debug("MigrateTitleComponent.migrate({})", resource.getPath());
            log.println("MigrateTitleComponent.migrate(" + resource.getPath() + ")");
            log.flush();
            renameAttribute(resource, "jcr:title", "title");
            // FIXME(hps,04.01.24) implement the other attributes as well
            return true;
        }
        return false;
    }

}

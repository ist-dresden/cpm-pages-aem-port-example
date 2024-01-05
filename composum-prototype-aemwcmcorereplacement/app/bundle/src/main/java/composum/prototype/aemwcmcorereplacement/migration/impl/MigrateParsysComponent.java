package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates wknd/components/parsys -> composum/prototype/aem-wcm-core-replacement/components/parsys .
 * <p>
 * AEM properties:
 * <ul>
 *     <li>./layout - defines the layout type, either simple (default) or responsiveGrid; if no value is defined, the component will fallback to the value defined by the component's policy</li>
 *     <li>./backgroundImageReference - defines the container background image.</li>
 *     <li>./backgroundColor - defines the container background color.</li>
 *     <li>./id - defines the component HTML ID attribute.</li>
 *     <li>./accessibilityLabel - defines an accessibility label for the container.</li>
 *     <li>./roleAttribute - defines a role attribute for the container.</li>
 * </ul>
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateParsysComponent extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateParsysComponent.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        if (replaceResourceType(resource, "wknd/components/container",
                "composum/prototype/aem-wcm-core-replacement/components/parsys")) {
            LOG.debug("MigrateParsysComponent.migrate({})", resource.getPath());
            log.println("MigrateParsysComponent.migrate(" + resource.getPath() + ")");
            log.flush();
            // FIXME(hps,04.01.24) implement the other attributes as well
            return true;
        }
        return false;
    }
}

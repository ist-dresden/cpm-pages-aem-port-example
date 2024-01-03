package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates wknd/components/text -> composum/pages/components/element/text  .
 * <p>
 * The AEM component has an attribute text that can be kept as is if textIsRich is set to true. Otherwise, we need to
 * convert the text to HTML, as there is no such equivalent at Pages.
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateTextComponent extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateTextComponent.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        if (replaceResourceType(resource, "wknd/components/text", "composum/pages/components/element/text")) {
            LOG.debug("Migrating {} to composum/pages/components/element/text", resource.getPath());
            log.println("MigrateTextComponent.migrate(" + resource.getPath() + ")");
            log.flush();
            // handle textIsRich=false
            if (!resource.getValueMap().get("textIsRich", false)) {
                String text = resource.getValueMap().get("text", "");
                resource.adaptTo(ModifiableValueMap.class).put("text", "<p>" + text + "</p>");
            }
            // FIXME(hps,03.01.24) what about <blockquote> - why doesn't that work in Composum? What to do?
            return true;
        }
        return false;
    }
}

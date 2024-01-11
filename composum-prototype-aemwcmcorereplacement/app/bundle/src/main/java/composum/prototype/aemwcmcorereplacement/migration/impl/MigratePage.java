package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migration: wknd/components/page -> core/wcm/components/page/v2/page
 * replaced by
 * composum/pages/components/page with template
 * apps/composum/prototype/aem-wcm-core-replacement/templates/page -> composum/pages/components/container/parsys
 *
 * @see "https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/page/v2/page"
 */
/* Composum Page:
<?xml version="1.0" encoding="UTF-8"?>
<jcr:root xmlns:cpp="http://sling.composum.com/pages/1.0"
        xmlns:jcr="http://www.jcp.org/jcr/1.0"
        xmlns:sling="http://sling.apache.org/jcr/sling/1.0"
        jcr:primaryType="cpp:Page">
    <jcr:content
            jcr:primaryType="cpp:PageContent"
            sling:resourceType="composum/pages/components/page"
            jcr:title="Pages Components Test Page"
            template="apps/composum/prototype/aem-wcm-core-replacement/templates/page">
        <main
                jcr:primaryType="cpp:Container"
                sling:resourceType="composum/pages/components/container/parsys"/>
    </jcr:content>
</jcr:root>
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigratePage extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigratePage.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) throws PersistenceException {
        // migrate the cpp:PageContent node - the cpp:Page just needed setting the primary type
        if (replaceResourceType(resource, "wknd/components/page", "composum/pages/components/page")) {
            resource.adaptTo(ModifiableValueMap.class).put("template", "apps/composum/prototype/aem-wcm-core-replacement/templates/page");
            logMigrationStart(resource, log);
            migrateCommonAttributes(resource, log);
            // the main paragraph system is typically named "main" in Composum, instead of root
            // rename the child
            Resource root = resource.getChild("root");
            Resource main = resource.getChild("main");
            if (root != null) {
                if (main != null) { // that cannot conceiveably happen. Alert the user.
                    throw new IllegalStateException("Both root and main child found in " + resource.getPath());
                }
                renameNode(root, "main");
            }
            return true;
        }
        return false;
    }

}

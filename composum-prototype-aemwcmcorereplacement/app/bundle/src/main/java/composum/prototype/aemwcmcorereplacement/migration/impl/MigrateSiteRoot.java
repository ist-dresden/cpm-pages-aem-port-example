package composum.prototype.aemwcmcorereplacement.migration.impl;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;
import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationService;

/**
 * Migrates AEM site roots to Composum sites.
 */
/* Structure of a Composum site:
* <?xml version="1.0" encoding="UTF-8"?>
<jcr:root xmlns:cpp="http://sling.composum.com/pages/1.0"
          xmlns:jcr="http://www.jcp.org/jcr/1.0"
          xmlns:sling="http://sling.apache.org/jcr/sling/1.0"
          jcr:primaryType="cpp:Site">
    <jcr:content
            jcr:primaryType="cpp:SiteConfiguration"
            sling:resourceType="composum/pages/stage/edit/site"/>
</jcr:root>
*/
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateSiteRoot extends AbstractAemWcmCoreMigrationMethod {

    @Override
    public boolean migrateSiteroot(org.apache.sling.api.resource.Resource resource, java.io.PrintWriter log) {
        logMigrationStart(resource, log);
        ModifiableValueMap modifiableProperties = resource.adaptTo(ModifiableValueMap.class);
        if (!modifiableProperties.containsKey(AemWcmCoreMigrationService.PROP_SITEROOT_MARKER)) {
            throw new IllegalArgumentException("Misusage: resource is not marked as a site root: " + resource.getPath());
        }
        modifiableProperties.remove(AemWcmCoreMigrationService.PROP_SITEROOT_MARKER);
        modifiableProperties.put("jcr:primaryType", "cpp:Site");
        Resource contentNode = resource.getChild("jcr:content");
        ModifiableValueMap cmvp = contentNode.adaptTo(ModifiableValueMap.class);
        cmvp.put("jcr:primaryType", "cpp:SiteConfiguration");
        cmvp.put("sling:resourceType", "composum/pages/stage/edit/site");
        return true;
    }

}

package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates wknd/components/teaser -> composum/pages/components/element/teaser.
 * Maps AEM teaser properties to Composum teaser properties.
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateTeaserComponent extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateTeaserComponent.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) throws IOException {
        try {
            if (replaceResourceType(resource, "wknd/components/teaser",
                    "composum/prototype/aem-wcm-core-replacement/components/teaser")) {
                LOG.debug("MigrateTeaserComponent.migrate({})", resource.getPath());
                log.println("MigrateTeaserComponent.migrate(" + resource.getPath() + ")");
                migrateCommonAttributes(resource, log);
                migrateTeaserAttributes(resource, log);
                return true;
            }
        } finally {
            log.flush();
        }
        return false;
    }

    // Migrate from AEM to Composum
    private void migrateTeaserAttributes(Resource resource, PrintWriter log) throws PersistenceException {
        ModifiableValueMap modifiableProperties = Objects.requireNonNull(resource.adaptTo(ModifiableValueMap.class));
        unsupported(resource, log, "altValueFromDAM", "imageFromPageImage", "altValueFromPageImage", "titleType", "isDecorative", "titleType");

        String linkPath = null;
        if (isTrue(resource, "actionsEnabled") && resource.getChild("actions") != null
                && resource.getChild("actions").listChildren().hasNext()) {
            Resource firstAction = resource.getChild("actions").listChildren().next();
            linkPath = firstAction.getValueMap().get("link", String.class);
            renameAttribute(firstAction, "link", resource, "link");
            renameAttribute(firstAction, "title", resource, "title");
        } else if (modifiableProperties.containsKey("linkURL")) {
            renameAttribute(resource, "linkURL", resource, "link");
            linkPath = modifiableProperties.get("linkURL", String.class);
            resource.adaptTo(ModifiableValueMap.class).put("title", modifiableProperties.get("jcr:title")); // ???
        }

        renameAttribute(resource, "pretitle", "subtitle");

        if (isTrue(resource, "titleFromPage")) {
            Resource linkedPage = resource.getResourceResolver().getResource(linkPath);
            if (linkedPage != null && linkPath.startsWith("/content")) {
                resource.adaptTo(ModifiableValueMap.class).put("title", linkedPage.getValueMap().get("jcr:content/jcr:title", String.class));
            } else {
                LOG.warn("WARNING: titleFromPage is true but no page is linked, for {}", resource.getPath());
                log.println("WARNING: titleFromPage is true but no page is linked, for " + resource.getPath());
            }
        } else {
            renameAttribute(resource, "jcr:title", "title");
        }

        if (isTrue(resource, "descriptionFromPage")) {
            Resource linkedPage = resource.getResourceResolver().getResource(linkPath);
            if (linkedPage != null && linkPath.startsWith("/content")) {
                resource.adaptTo(ModifiableValueMap.class).put("text", linkedPage.getValueMap().get("jcr:content/jcr:description", String.class));
            } else {
                LOG.warn("WARNING: descriptionFromPage is true but no page is linked, for {}", resource.getPath());
                log.println("WARNING: descriptionFromPage is true but no page is linked, for " + resource.getPath());
            }
        } else {
            renameAttribute(resource, "jcr:description", "text");
        }

        if (modifiableProperties.containsKey("alt")) {
            Resource image = getOrCreateSubresource(resource, "image", "composum/pages/components/element/image");
            renameAttribute(resource, "alt", image, "alt");
        }

        if (modifiableProperties.containsKey("fileReference")) {
            // check whether it ends in .jpg or .jpeg or .png or .gif ignoring case, otherwise it's a video
            String fileReference = modifiableProperties.get("fileReference", "");
            if (fileReference.toLowerCase().endsWith(".jpg") || fileReference.toLowerCase().endsWith(".jpeg") ||
                    fileReference.toLowerCase().endsWith(".png") || fileReference.toLowerCase().endsWith(".gif")) {
                Resource image = getOrCreateSubresource(resource, "image", "composum/pages/components/element/image");
                String imagePath = fileReference + "/jcr:content/renditions/original";
                renameAttribute(resource, "fileReference", image, "imageRef");
                image.adaptTo(ModifiableValueMap.class).put("imageRef", imagePath);
            } else {
                log.println("WARNING: videos are not yet supported, for " + resource.getPath());
                LOG.warn("WARNING: videos are not yet supported, for {}", resource.getPath());
            }
        }
    }

}

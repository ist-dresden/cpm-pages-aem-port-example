package composum.prototype.aemwcmcorereplacement.migration.impl;

import java.io.PrintWriter;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composum.prototype.aemwcmcorereplacement.migration.AemWcmCoreMigrationMethod;

/**
 * Migrates wknd/components/image -> composum/prototype/aem-wcm-core-replacement/components/image based on composum/pages/components/element/image  .
 * <p>
 * Composum properties: imageRef, title, alt, copyright, copyrightUrl, license, licenseUrl
 * </p>
 * <p>
 * AEM properties:
 *  <ul>
 *      <li>./fileReference property or file child node - will store either a reference to the image file, or the image file</li>
 *      <li>./isDecorative - if set to true, then the image will be ignored by assistive technology</li>
 *      <li>./alt - defines the value of the HTML alt attribute (not needed if ./isDecorative is set to true)</li>
 *      <li>./altValueFromDAM - if true, the HTML alt attribute is inherited from the DAM asset.</li>
 *      <li>./linkURL - allows defining a URL to which the image will link to</li>
 *      <li>./jcr:title - defines the value of the HTML title attribute or the value of the caption, depending on the value of</li>
 *      <li>./displayPopupTitle - if set to true it will render the value of the ./jcr:title property through the HTML title</li>
 *      <li>attribute, otherwise a caption will be rendered</li>
 *      <li>./id - defines the component HTML ID attribute.</li>
 *      <li>./dmPresetType - defines the type of Dynamic Media image rendering, possible values are imagePreset, smartCrop.</li>
 *      <li>./imagePreset - defines the name for the Dynamic Media Image Preset to apply to the Dynamic Media image URL.</li>
 *      <li>./smartCropRendition - defines how Dynamic Media Smart Crop image renders. SmartCrop:Auto means that the component will</li>
 *      <li>automatically select Smart Crop rendition which fits the container size better; the name of specific Smart Crop</li>
 *      <li>rendition will force the component to render that image rendition only.</li>
 *      <li>./imageModifiers - defines additional Dynamic Media Image Serving commands separated by '&amp;'. Field gives complete</li>
 *      <li>flexibility to change Dynamic Media image rendering.</li>
 *      <li>./imageFromPageImage - if true, the image is inherited from the featured image of either the linked page if ./linkURL is</li>
 *      <li>set or the current page.</li>
 *      <li>./altValueFromPageImage - if true and if ./imageFromPageImage is true, the HTML alt attribute is inherited from the</li>
 *      <li>featured image of either the linked page if ./linkURL is set or the current page.</li>
 *      <li>./disableLazyLoading - if true the lazy loading of the image is disabled regardless of the lazy loading setting in the</li>
 *      <li>design policy.</li>
 *      <li>./titleValueFromDAM - if true, the HTML title attribute is inherited from the DAM asset.</li>
 *      <li>./imageCrop - defines the image crop to use for the image.</li>
 *      <li>./cq:panelTitle - defines the value of the HTML title attribute or the value of the caption, depending on the value of</li>
 *      <li>./displayPopupTitle, but only if the component is used in a panel.</li>
 *  </ul>
 *
 * <p>
 * We currently use a shortcut: migrate fileReference to the jcr:content/renditions/original of the dam:Asset,
 * alt stays as it is, if displayPopupTitle is true, migrate jcr:title as alt, else to title .
 * The other attributes stay untouched and are unused for now.
 */
@Component(service = AemWcmCoreMigrationMethod.class)
public class MigrateImageComponent extends AbstractAemWcmCoreMigrationMethod {

    private static final Logger LOG = LoggerFactory.getLogger(MigrateImageComponent.class);

    @Override
    public boolean migrate(Resource resource, PrintWriter log) {
        if (replaceResourceType(resource, "wknd/components/image",
                "composum/prototype/aem-wcm-core-replacement/components/image")) {
            LOG.debug("MigrateImageComponent.migrate({})", resource.getPath());
            log.println("MigrateImageComponent.migrate(" + resource.getPath() + ")");
            log.flush();
            migrateCommonAttributes(resource, log);
            ModifiableValueMap mvm = resource.adaptTo(ModifiableValueMap.class);
            String fileReference = mvm.get("fileReference", "");
            boolean migrated = false;
            if (!fileReference.isEmpty()) {
                Resource asset = resource.getResourceResolver().getResource(fileReference);
                if (asset != null) {
                    Resource original = asset.getChild("jcr:content/renditions/original");
                    if (original != null) {
                        mvm.put("imageRef", original.getPath());
                        migrated = true;
                    }
                }
            }
            String titleValue = mvm.get("jcr:title", String.class);
            if (titleValue != null && !titleValue.isEmpty()) {
                if ("true".equals(mvm.get("displayPopupTitle", ""))) {
                    mvm.put("alt", titleValue);
                } else {
                    mvm.put("title", mvm.get("jcr:title", ""));
                }
            }
            if (!migrated) {
                LOG.warn("ERROR: MigrateImageComponent.migrate({}): no imageRef found", resource.getPath());
                log.println("ERROR: MigrateImageComponent.migrate(" + resource.getPath() + "): no imageRef found");
                log.flush();
            }
            return true;
        }
        return false;
    }
}

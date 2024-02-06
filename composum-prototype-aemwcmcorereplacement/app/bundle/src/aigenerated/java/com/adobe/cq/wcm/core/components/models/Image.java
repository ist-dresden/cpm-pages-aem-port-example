// AIGenVersion(e81604dbd7, generalsystemmessage.prompt-1.0, generateModelClass.md-1.4, AbstractComponent.java-a687d5d32e, Image.md-01f5b7d659, com.adobe.cq.wcm.core.components.models.Image.md-47c94eec24)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.Collections;
import java.util.List;

/**
 * Represents the Image component's Sling Model.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Image extends AbstractComponent {

    @ValueMapValue
    private String fileReference;

    @ValueMapValue
    private String alt;

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private boolean displayPopupTitle;

    /**
     * Returns the file reference of the image.
     *
     * @return the file reference
     */
    public String getFileReference() {
        return fileReference;
    }

    /**
     * Returns the alt text of the image.
     *
     * @return the alt text
     */
    public String getAlt() {
        return alt;
    }

    /**
     * Returns the link URL of the image.
     *
     * @return the link URL
     */
    public String getLink() {
        return linkURL;
    }

    /**
     * Returns the title of the image.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Indicates if the title should be displayed as a popup.
     *
     * @return true if the title should be displayed as a popup, false otherwise
     */
    public boolean isDisplayPopupTitle() {
        return displayPopupTitle;
    }

    /**
     * Returns the image source URL.
     *
     * @return the image source URL
     */
    public String getSrc() {
        return fileReference != null ? fileReference + "/jcr:content/renditions/original" : null;
    }

    /**
     * Returns the JSON string representing image properties for data layer integration.
     *
     * @return the JSON string
     */
    public String getJson() {
        return null; // TODO: Implement based on the specification
    }

    /**
     * Indicates if lazy loading is enabled.
     *
     * @return true if lazy loading is enabled, false otherwise
     */
    public boolean isLazyEnabled() {
        return false; // TODO: Implement based on the specification
    }

    /**
     * Returns the lazy threshold.
     *
     * @return the lazy threshold
     */
    public int getLazyThreshold() {
        return -1; // TODO: Implement based on the specification
    }

    /**
     * Returns the URI template for generating image URLs with variable width.
     *
     * @return the URI template
     */
    public String getSrcUriTemplate() {
        return null; // TODO: Implement based on the specification
    }

    /**
     * Returns an array of allowed rendition widths.
     *
     * @return the array of widths
     */
    public int[] getWidths() {
        return new int[0]; // TODO: Implement based on the specification
    }

    /**
     * Indicates if the image is a Dynamic Media image.
     *
     * @return true if the image is a Dynamic Media image, false otherwise
     */
    public boolean isDmImage() {
        return false; // TODO: Implement based on the specification
    }

    /**
     * Returns how Dynamic Media Smart Crop image renders.
     *
     * @return the Smart Crop rendition
     */
    public String getSmartCropRendition() {
        return null; // TODO: Implement based on the specification
    }

    /**
     * Returns a unique identifier for the image.
     *
     * @return the UUID
     */
    public String getUuid() {
        return null; // TODO: Implement based on the specification
    }

    /**
     * Returns an object containing data layer information.
     *
     * @return the data object
     */
    public Object getData() {
        return null; // TODO: Implement based on the specification
    }
}

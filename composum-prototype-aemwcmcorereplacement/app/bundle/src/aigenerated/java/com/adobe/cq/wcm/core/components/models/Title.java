// AIGenVersion(93638b83f4, generalsystemmessage.prompt-1.0, generateModelClass.md-1.3, title.html-1f42a00d67, title.html-8a4f9c10ce, AbstractComponent.java-a687d5d32e, Title.md-15e499faba)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Sling Model for the Title component that provides the properties discussed in the specification file.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Title extends AbstractComponent {

    @ValueMapValue
    private String jcrTitle;

    @ValueMapValue
    private String type;

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private boolean linkDisabled;

    @ValueMapValue(name = "linkAccessibilityLabel")
    private String linkAccessibilityLabel;

    @ValueMapValue(name = "linkTitleAttribute")
    private String linkTitleAttribute;

    /**
     * Gets the title text.
     *
     * @return the title text.
     */
    public String getText() {
        return jcrTitle;
    }

    /**
     * Gets the HTML heading element type.
     *
     * @return the HTML heading element type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the URL for the title link.
     *
     * @return the URL for the title link.
     */
    public String getLinkURL() {
        return linkURL;
    }

    /**
     * Checks if the title link is disabled.
     *
     * @return true if the title link is disabled, false otherwise.
     */
    public boolean isLinkDisabled() {
        return linkDisabled;
    }

    /**
     * Gets the accessibility label for the title's link.
     * This member does not have a corresponding JCR attribute and should return null.
     *
     * @return null.
     */
    public String getLinkAccessibilityLabel() {
        return linkAccessibilityLabel;
    }

    /**
     * Gets the title attribute for the title's link.
     * This member does not have a corresponding JCR attribute and should return null.
     *
     * @return null.
     */
    public String getLinkTitleAttribute() {
        return linkTitleAttribute;
    }

    /**
     * Gets the JSON string for data layer integration.
     * This member does not have a corresponding JCR attribute and should return null.
     *
     * @return null.
     */
    public String getDataJson() {
        return null;
    }
}
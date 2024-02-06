// AIGenVersion(04acb694e1, generalsystemmessage.prompt-1.0, generateModelClass.md-1.4, AbstractComponent.java-a687d5d32e, Title.md-e2ee8b1cc7)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Title component model that defines the properties for a title component.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Title extends AbstractComponent {

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String type;

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String linkAccessibilityLabel;

    @ValueMapValue
    private String linkTitleAttribute;

    /**
     * Gets the text of the title.
     *
     * @return the title text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the HTML heading element type for the title.
     *
     * @return the heading type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the URL to which the title links.
     *
     * @return the link URL
     */
    public String getLinkURL() {
        return linkURL;
    }

    /**
     * Gets the component HTML ID attribute.
     *
     * @return the HTML ID
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Gets the accessibility label for the title's link.
     *
     * @return the accessibility label
     */
    public String getLinkAccessibilityLabel() {
        return linkAccessibilityLabel;
    }

    /**
     * Gets the title attribute for the title's link.
     *
     * @return the title attribute
     */
    public String getLinkTitleAttribute() {
        return linkTitleAttribute;
    }

    /**
     * Indicates whether the title link is disabled.
     *
     * @return always returns {@code null}
     */
    public Boolean getLinkDisabled() {
        // TODO: Implement logic to determine if the link is disabled
        return null;
    }

    /**
     * Gets the data object for data layer integration.
     *
     * @return always returns {@code null}
     */
    public Object getData() {
        // TODO: Implement data layer integration
        return null;
    }
}
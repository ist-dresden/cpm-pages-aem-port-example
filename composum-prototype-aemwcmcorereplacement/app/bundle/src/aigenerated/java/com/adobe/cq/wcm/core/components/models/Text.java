// AIGenVersion(19f228d70a, generalsystemmessage.prompt-1.0, generateModelClass.md-1.4, AbstractComponent.java-a687d5d32e, Text.md-2992911859)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Provides the properties for a Text component.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Text extends AbstractComponent {

    @ValueMapValue
    private String text;

    @ValueMapValue(name = "textIsRich")
    private boolean isRichText;

    /**
     * Returns the text to be rendered.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Determines if the rendered text is rich text.
     *
     * @return true if the text is rich text, false otherwise
     */
    public boolean isRichText() {
        return isRichText;
    }

    /**
     * Returns the component HTML ID attribute.
     *
     * @return the component ID
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     * Returns the data for data layer integration.
     * TODO: Implement data layer integration
     *
     * @return null as data layer integration is not yet implemented
     */
    public Object getData() {
        return null;
    }
}
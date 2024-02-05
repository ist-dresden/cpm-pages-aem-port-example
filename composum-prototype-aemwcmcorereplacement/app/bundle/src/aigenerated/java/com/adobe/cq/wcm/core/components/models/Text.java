// AIGenVersion(36adc70dbb, generalsystemmessage.prompt-1.0, generateModelClass.md-1.3, text.html-c5e39866f6, AbstractComponent.java-2f36bcd8ee, Text.md-506b8f147e)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
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
     * @return The text.
     */
    public String getText() {
        return text;
    }

    /**
     * Determines if the rendered text is rich text.
     *
     * @return True if the text is rich text, false otherwise.
     */
    public boolean isRichText() {
        return isRichText;
    }

    /**
     * Returns the data for the data layer integration.
     * This method does not correspond to a JCR attribute and thus returns null.
     *
     * @return null as it does not correspond to a JCR attribute.
     */
    public Object getData() {
        return null;
    }
}
// AIGenVersion(67dbdfa4d8, generalsystemmessage.prompt-1.0, generateModelClass.md-1.3, text.html-146972031c, text.html-c5e39866f6, AbstractComponent.java-a687d5d32e, Text.md-10742b3ab0)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Provides the properties for the Text component.
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
     * @return {@code true} if the text is rich text, otherwise {@code false}
     */
    public boolean isRichText() {
        return isRichText;
    }

    /**
     * Returns the data layer JSON.
     *
     * @return the data layer JSON, or {@code null} if not applicable
     */
    public String getDataJson() {
        return null;
    }
}
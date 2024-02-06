// AIGenVersion(f704beed03, generalsystemmessage.prompt-1.0, generateModelClass.md-1.3, separator.html-bd91b49ac4, AbstractComponent.java-a687d5d32e, Separator.md-30703a8054)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Model for the Separator component.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Separator extends AbstractComponent {

    @ValueMapValue
    private boolean isDecorative;

    /**
     * Checks if the separator is decorative.
     * 
     * @return {@code true} if the separator is decorative, otherwise {@code false}.
     */
    public boolean isDecorative() {
        return isDecorative;
    }
}
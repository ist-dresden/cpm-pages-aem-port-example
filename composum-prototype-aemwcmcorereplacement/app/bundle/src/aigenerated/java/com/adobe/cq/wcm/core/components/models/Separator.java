// AIGenVersion(4023081614, generalsystemmessage.prompt-1.0, generateModelClass.md-1.4, AbstractComponent.java-a687d5d32e, Separator.md-dec09e082e)

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
     * Returns if the separator is decorative.
     * 
     * @return {@code true} if the separator is decorative, otherwise {@code false}.
     */
    public boolean isDecorative() {
        return isDecorative;
    }
}
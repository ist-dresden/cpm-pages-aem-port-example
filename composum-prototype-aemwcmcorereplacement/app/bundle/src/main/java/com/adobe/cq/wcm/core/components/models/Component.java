package com.adobe.cq.wcm.core.components.models;

import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CPMADD Substutite for wcm core class.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Component extends AbstractComponent {

    private static final Logger LOG = LoggerFactory.getLogger(Component.class);

    /** A map of properties for the component. */
    public Map<String, Object> getProperties() {
        return this.resource.getValueMap();
    }

}

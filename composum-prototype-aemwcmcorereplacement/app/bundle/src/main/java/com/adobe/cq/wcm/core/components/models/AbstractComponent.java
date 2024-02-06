package com.adobe.cq.wcm.core.components.models;

import java.lang.ref.WeakReference;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Base class for the models. CAUTION: use
 * <code>@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)</code>
 */
public abstract class AbstractComponent {

    /** Don't use - use {@link #requestRef}. */
    @Self
    private SlingHttpServletRequest temporarilyInjectedRequest;

    private WeakReference<SlingHttpServletRequest> requestRef;

    @SlingObject
    protected Resource resource;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected String id;

    /** Avoids https://issues.apache.org/jira/browse/SLING-7586 */
    @PostConstruct
    private void initRequestRef() {
        requestRef = new WeakReference<>(temporarilyInjectedRequest);
        temporarilyInjectedRequest = null;
    }

    /**
     * Returns an ID for the component. If there is no id set, we generate one from the path.
     */
    public String getId() {
        if (id == null) {
            String resourceType = resource.getResourceType();
            String hash = DigestUtils.sha256Hex(resource.getPath()).substring(0, 10);
            if (resourceType != null) {
                id = resourceType.substring(resourceType.lastIndexOf('/') + 1) + "-" + hash;
            } else {
                id = "unknown-" + hash;
            }
        }
        return id;
    }

}

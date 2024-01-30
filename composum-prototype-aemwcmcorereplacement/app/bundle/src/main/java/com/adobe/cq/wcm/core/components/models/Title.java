package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
1. `./jcr:title` - will store the text of the title to be rendered
2. `./type` - will store the HTML heading element type which will be used for rendering; if no value is defined, the component will fallback
to the value defined by the component's policy
3. `./linkURL` - will allow definition of a content page path, external URL or page anchor for linking the title.
4. `./id` - defines the component HTML ID attribute.
5. `./linkAccessibilityLabel` - defines an accessibility label for the the title's link.
6. `./linkTitleAttribute` - defines a title attribute for the the title's link.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Title extends AbstractComponent {

    @ValueMapValue(name = "jcr:title")
    private String title;

    @ValueMapValue
    private String type;

    @ValueMapValue
    private String linkURL;

    public String getText() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getLinkURL() {
        return linkURL;
    }

    // FIXME link is not handled yet.

}

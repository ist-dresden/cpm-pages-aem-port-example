// AIGenVersion(ee84989028, generalsystemmessage.prompt-1.0, generateModelClass.md-1.4, AbstractComponent.java-a687d5d32e, Teaser.md-54eeb8e627, com.adobe.cq.wcm.core.components.models.Teaser.md-1.1)

package com.adobe.cq.wcm.core.components.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.Collections;
import java.util.List;

/**
 * Defines the Sling Model for the Teaser component.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Teaser extends AbstractComponent {

    @ValueMapValue
    private boolean actionsDisabled;

    @ValueMapValue
    private boolean pretitleHidden;

    @ValueMapValue
    private boolean titleHidden;

    @ValueMapValue
    private boolean descriptionHidden;

    @ValueMapValue
    private boolean imageLinkHidden;

    @ValueMapValue
    private boolean titleLinkHidden;

    @ValueMapValue
    private String titleType;

    @ValueMapValue
    private boolean showTitleType;

    @ValueMapValue
    private boolean actionsEnabled;

    // TODO: Implement the logic to fetch actions from the child nodes
    private List<Action> actions;

    @ValueMapValue
    private Resource imageResource;

    @ValueMapValue
    private String linkURL;

    @ValueMapValue
    private String pretitle;

    @ValueMapValue(name = "jcr:title")
    private String title;

    @ValueMapValue
    private boolean titleFromPage;

    @ValueMapValue(name = "jcr:description")
    private String description;

    @ValueMapValue
    private boolean descriptionFromPage;

    /**
     * Gets whether actions are disabled.
     *
     * @return true if actions are disabled, false otherwise.
     */
    public boolean isActionsDisabled() {
        return actionsDisabled;
    }

    /**
     * Gets whether the pretitle is hidden.
     *
     * @return true if the pretitle is hidden, false otherwise.
     */
    public boolean isPretitleHidden() {
        return pretitleHidden;
    }

    /**
     * Gets whether the title is hidden.
     *
     * @return true if the title is hidden, false otherwise.
     */
    public boolean isTitleHidden() {
        return titleHidden;
    }

    /**
     * Gets whether the description is hidden.
     *
     * @return true if the description is hidden, false otherwise.
     */
    public boolean isDescriptionHidden() {
        return descriptionHidden;
    }

    /**
     * Gets whether the image link is hidden.
     *
     * @return true if the image link is hidden, false otherwise.
     */
    public boolean isImageLinkHidden() {
        return imageLinkHidden;
    }

    /**
     * Gets whether the title link is hidden.
     *
     * @return true if the title link is hidden, false otherwise.
     */
    public boolean isTitleLinkHidden() {
        return titleLinkHidden;
    }

    /**
     * Gets the title type.
     *
     * @return the title type.
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * Gets whether the title type is shown.
     *
     * @return true if the title type is shown, false otherwise.
     */
    public boolean isShowTitleType() {
        return showTitleType;
    }

    /**
     * Gets whether actions are enabled.
     *
     * @return true if actions are enabled, false otherwise.
     */
    public boolean isActionsEnabled() {
        return actionsEnabled;
    }

    /**
     * Gets the list of actions.
     *
     * @return the list of actions.
     */
    public List<Action> getActions() {
        // TODO: Implement fetching logic
        return Collections.emptyList();
    }

    /**
     * Gets the image resource.
     *
     * @return the image resource.
     */
    public Resource getImageResource() {
        return imageResource;
    }

    /**
     * Gets the link URL.
     *
     * @return the link URL.
     */
    public String getLinkURL() {
        return linkURL;
    }

    /**
     * Gets the pretitle.
     *
     * @return the pretitle.
     */
    public String getPretitle() {
        return pretitle;
    }

    /**
     * Gets the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets whether the title is from the page.
     *
     * @return true if the title is from the page, false otherwise.
     */
    public boolean isTitleFromPage() {
        return titleFromPage;
    }

    /**
     * Gets the description.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets whether the description is from the page.
     *
     * @return true if the description is from the page, false otherwise.
     */
    public boolean isDescriptionFromPage() {
        return descriptionFromPage;
    }

    /**
     * Inner class representing an action.
     */
    public static class Action {

        @ValueMapValue
        private String link;

        @ValueMapValue
        private String text;

        /**
         * Gets the link of the action.
         *
         * @return the link.
         */
        public String getLink() {
            return link;
        }

        /**
         * Gets the text of the action.
         *
         * @return the text.
         */
        public String getText() {
            return text;
        }
    }
}
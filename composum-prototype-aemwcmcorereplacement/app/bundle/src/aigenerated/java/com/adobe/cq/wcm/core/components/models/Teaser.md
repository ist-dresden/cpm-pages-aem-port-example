<!-- AIGenVersion(54eeb8e627, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.4, README.md-b084bdde18, teaser.html-0a159939e2, com.adobe.cq.wcm.core.components.models.Teaser.md-1.1) -->

Specification for `com.adobe.cq.wcm.core.components.models.Teaser`
====
Teaser component written in HTL, allowing definition of an image, title, rich text description and actions/links. Teaser variations can include some or all of these elements.

### Classname
`com.adobe.cq.wcm.core.components.models.Teaser`

### Usages in *.html files
```html
<div data-sly-use.teaser="com.adobe.cq.wcm.core.components.models.Teaser"
     data-sly-test.hasContent="${teaser.imageResource || teaser.pretitle || teaser.title || teaser.description || teaser.actions.size > 0}"
     id="${teaser.id}"
     class="cmp-teaser${!wcmmode.disabled && teaser.imageResource ? ' cq-dd-image' : ''}"
     data-cmp-data-layer="${teaser.data.json}">
```

```html
<sly data-sly-call="${imageTemplate.image @ teaser=teaser}"></sly>
<sly data-sly-call="${pretitleTemplate.pretitle @ teaser=teaser}"></sly>
<sly data-sly-call="${titleTemplate.title @ teaser=teaser}"></sly>
<sly data-sly-call="${descriptionTemplate.description @ teaser=teaser}"></sly>
<sly data-sly-call="${actionsTemplate.actions @ teaser=teaser}"></sly>
```

### Model Members Specification

| JCR Attribute      | Java Property     | Data Type         | Description                                                                                   |
|--------------------|-------------------|-------------------|-----------------------------------------------------------------------------------------------|
| ./actionsDisabled  | actionsDisabled   | Boolean           | Defines whether or not Call-to-Actions are disabled                                           |
| ./pretitleHidden   | pretitleHidden    | Boolean           | Defines whether or not the pretitle is hidden                                                 |
| ./titleHidden      | titleHidden       | Boolean           | Defines whether or not the title is hidden                                                    |
| ./descriptionHidden| descriptionHidden | Boolean           | Defines whether or not the description is hidden                                              |
| ./imageLinkHidden  | imageLinkHidden   | Boolean           | Defines whether or not the image link is hidden                                               |
| ./titleLinkHidden  | titleLinkHidden   | Boolean           | Defines whether or not the title link is hidden                                               |
| ./titleType        | titleType         | String            | Stores the value for this title's HTML element type                                           |
| ./showTitleType    | showTitleType     | Boolean           | Defines whether or not the title tab dropdown menu is shown                                   |
| ./actionsEnabled   | actionsEnabled    | Boolean           | Property that defines whether or not the teaser has Call-to-Action elements                   |
| ./actions          | actions           | List<Action>      | Child node where the Call-to-Action elements are stored                                       |
| ./fileReference    | imageResource     | Resource          | Property or `file` child node - will store either a reference to the image file, or the image file |
| ./linkURL          | linkURL           | String            | Link applied to teaser elements. URL or path to a content page                                |
| ./pretitle         | pretitle          | String            | Defines the value of the teaser pretitle                                                      |
| ./jcr:title        | title             | String            | Defines the value of the teaser title and HTML `title` attribute of the teaser image          |
| ./titleFromPage    | titleFromPage     | Boolean           | Defines whether or not the title value is taken from the linked page                          |
| ./jcr:description  | description       | String            | Defines the value of the teaser description                                                   |
| ./descriptionFromPage | descriptionFromPage | Boolean        | Defines whether or not the description value is taken from the linked page                    |
| ./id               | id                | String            | Defines the component HTML ID attribute                                                       |
|                    | data.json         | String            | Data layer JSON object for analytics                                                          |

### Action Class Specification

| JCR Attribute | Java Property | Data Type | Description                           |
|---------------|---------------|-----------|---------------------------------------|
| link          | link          | String    | Property that stores the Call-to-Action link |
| text          | text          | String    | Property that stores the Call-to-Action text |
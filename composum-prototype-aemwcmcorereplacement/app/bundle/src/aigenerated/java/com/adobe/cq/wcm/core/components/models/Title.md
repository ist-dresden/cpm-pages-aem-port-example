<!-- AIGenVersion(e2ee8b1cc7, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.4, README.md-cb9d0e44ab, title.html-1f42a00d67, title.html-8a4f9c10ce) -->

Specification for `com.adobe.cq.wcm.core.components.models.Title`
====
Title component written in HTL, allowing to define a section heading.

### Classname
`com.adobe.cq.wcm.core.components.models.Title`

### Usages in *.html files
- `data-sly-use.title="com.adobe.cq.wcm.core.components.models.Title"` (v1/title.html and v2/title.html)
- `<h1 data-sly-use.title="com.adobe.cq.wcm.core.components.models.Title" ...>${text}</h1>` (v1/title.html)
- `<div data-sly-use.title="com.adobe.cq.wcm.core.components.models.Title" ...>${text}</div>` (v2/title.html)
- `<h1 class="cmp-title__text" data-sly-element="${title.type}">...</h1>` (v2/title.html)
- `id="${title.id}"` (v2/title.html)
- `data-cmp-data-layer="${title.data.json}"` (v2/title.html)

### Model Properties Table

| JCR Attribute            | Java Property          | Data Type | Description                                                                 |
|--------------------------|------------------------|-----------|-----------------------------------------------------------------------------|
| ./jcr:title              | text                   | String    | will store the text of the title to be rendered                             |
| ./type                   | type                   | String    | will store the HTML heading element type which will be used for rendering  |
| ./linkURL                | linkURL                | String    | will allow definition of a content page path, external URL or page anchor  |
| ./id                     | id                     | String    | defines the component HTML ID attribute                                     |
| ./linkAccessibilityLabel | linkAccessibilityLabel | String    | defines an accessibility label for the title's link                         |
| ./linkTitleAttribute     | linkTitleAttribute     | String    | defines a title attribute for the title's link                              |
|                          | linkDisabled           | Boolean   | defines whether or not the title link is disabled (inferred from usage)    |
|                          | data                   | Object    | object for data layer integration (inferred from usage)                    |

### Data Object Properties Table (for `data` object)

| JCR Attribute | Java Property | Data Type | Description                                   |
|---------------|---------------|-----------|-----------------------------------------------|
|               | json          | String    | JSON string representation for data layer     |
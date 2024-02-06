<!-- AIGenVersion(15e499faba, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.3, README.md-cb9d0e44ab, title.html-1f42a00d67, title.html-8a4f9c10ce) -->

Specification for `com.adobe.cq.wcm.core.components.models.Title`
====
Title component written in HTL, allowing to define a section heading.

### Classname
`com.adobe.cq.wcm.core.components.models.Title`

### Usages in *.html files
- **data-sly-use.title="com.adobe.cq.wcm.core.components.models.Title"** in both v1 and v2 title.html files.
- **${title.text}** used to display the title text.
- **${title.type}** used to determine the HTML element (`h1` - `h6`).
- **${title.id}** used to set the HTML ID attribute.
- **${title.linkURL}** used to set the href attribute for the title link.
- **${title.linkDisabled}** used to conditionally unwrap the anchor tag.
- **${title.data.json}** used for data layer integration.
- **${title.link.htmlAttributes['aria-label']}** and **${title.link.htmlAttributes['title']}** used for accessibility and SEO.

### Table of Model Members

| JCR Attribute            | Java Property                  | Description                                                                 |
|--------------------------|--------------------------------|-----------------------------------------------------------------------------|
| ./jcr:title              | text                           | will store the text of the title to be rendered                             |
| ./type                   | type                           | will store the HTML heading element type which will be used for rendering   |
| ./linkURL                | linkURL                        | will allow definition of a content page path, external URL or page anchor   |
| ./id                     | id                             | defines the component HTML ID attribute                                     |
| ./linkDisabled           | linkDisabled                   | defines whether or not the title link is disabled                           |
|                          | data.json                      | used for data layer integration, not directly tied to a JCR attribute       |
| ./linkAccessibilityLabel | link.htmlAttributes['aria-label'] | defines an accessibility label for the title's link, not directly tied to a JCR attribute |
| ./linkTitleAttribute     | link.htmlAttributes['title']   | defines a title attribute for the title's link, not directly tied to a JCR attribute |
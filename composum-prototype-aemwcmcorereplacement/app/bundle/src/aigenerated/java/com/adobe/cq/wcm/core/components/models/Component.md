<!-- AIGenVersion(5db99ecaa1, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.4, README.md-c085f77001, embed.html-7ac2e0c719, title.html-8a4f9c10ce, separator.html-bd91b49ac4, image.html-a1cb9b0eac, contentfragmentlist.html-1588d98055, button.html-72c3ca57fa, download.html-b96985353f, list.html-b40becf9af, languagenavigation.html-5f53cfa026, text.html-c5e39866f6, breadcrumb.html-f4c7ffeb4a, experiencefragment.html-73d23fd393) -->

Specification for `com.adobe.cq.wcm.core.components.models.Component`
---

The `com.adobe.cq.wcm.core.components.models.Component` Sling Model is designed for use with various components in Adobe
Experience Manager (AEM). It provides a standardized way to access component properties and functionalities, including
but not limited to, data layer integration for analytics, unique component identifiers, and other component-specific
configurations.

### Classname

`com.adobe.cq.wcm.core.components.models.Component`

### Usages in *.html files

- `data-sly-use.component="com.adobe.cq.wcm.core.components.models.Component"`
- `<div id="${component.id}" class="cmp-experiencefragment cmp-experiencefragment--${fragment.name}">`
- `<nav id="${component.id}" class="cmp-languagenavigation" aria-label="${languageNavigation.accessibilityLabel}">`
- `<ul id="${component.id}" data-cmp-data-layer="${list.data.json}" class="cmp-list">`
- `<div id="${component.id}" data-cmp-data-layer="${textModel.data.json}" class="cmp-text">`
- `<nav id="${component.id}" class="cmp-breadcrumb" aria-label="${'Breadcrumb' @ i18n}" data-cmp-data-layer="${breadcrumb.data.json}">`
- `<div id="${component.id}" class="cmp-download${!wcmmode.disabled ? ' cq-dd-file' : ''}">`
- `<button id="${component.id}" class="cmp-button" data-cmp-data-layer="${button.data.json}">`
- `<section id="${component.id}" class="cmp-contentfragmentlist">`
- `<div id="${component.id}" class="cmp-image${!wcmmode.disabled ? ' cq-dd-image' : ''}" data-cmp-data-layer="${image.data.json}">`
- `<nav data-sly-use.component="com.adobe.cq.wcm.core.components.models.Component" id="${component.id}" class="cmp-breadcrumb" aria-label="${'Breadcrumb' @ i18n}" data-sly-test="${breadcrumb.items.size > 0}" data-cmp-data-layer="${breadcrumb.data.json}">`
- `data-emptytext="${component.properties.jcr:title}${emptyTextAppend && ' - '}${emptyTextAppend}"></div>`

### Table for `com.adobe.cq.wcm.core.components.models.Component`

| JCR Attribute | Java Property | Data Type | Description                                                                   |
|---------------|---------------|-----------|-------------------------------------------------------------------------------|
|               | id            | String    | A unique identifier for the component instance, used for HTML `id` attribute. |
|               | data.json     | Object    | A JSON object representing the data layer for the component.                  |
|               | properties    | Object    | A map of properties for the component.                                        |

Note: The `data.json` property is used for integrating with the data layer for analytics and other purposes. It is not
directly tied to a JCR attribute but is crucial for advanced functionalities and integrations.

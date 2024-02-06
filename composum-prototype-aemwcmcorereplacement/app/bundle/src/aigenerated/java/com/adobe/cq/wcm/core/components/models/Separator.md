<!-- AIGenVersion(dec09e082e, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.4, README.md-c3d04d41b3, separator.html-bd91b49ac4) -->

Specification for `com.adobe.cq.wcm.core.components.models.Separator`
---

The Separator component uses the `com.adobe.cq.wcm.core.components.models.Separator` Sling Model as its Use-object. It allows the addition of a horizontal rule element for separating content sections.

### Classname
`com.adobe.cq.wcm.core.components.models.Separator`

### Usages in *.html files
- `data-sly-use.separator="com.adobe.cq.wcm.core.components.models.Separator"`
- `<div id="${component.id}" class="cmp-separator">`
- `<hr class="cmp-separator__horizontal-rule" data-sly-attribute.role="${separator.decorative ? 'none': ''}" data-sly-attribute.aria-hidden="${separator.decorative ? 'true': ''}"/>`

### Table for `com.adobe.cq.wcm.core.components.models.Separator`

| JCR Attribute | Java Property | Data Type | Description                                                                 |
|---------------|---------------|-----------|-----------------------------------------------------------------------------|
| ./id          | id            | String    | Defines the component HTML ID attribute.                                    |
| ./isDecorative| decorative    | Boolean   | If set to `true`, then the separator will be ignored by assistive technology|
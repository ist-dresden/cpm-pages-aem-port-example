<!-- AIGenVersion(30703a8054, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.3, README.md-c3d04d41b3, separator.html-bd91b49ac4) -->

Specification for `com.adobe.cq.wcm.core.components.models.Separator`
---

The Separator component allows the addition of a horizontal rule element for separating content sections.

### Classname
`com.adobe.cq.wcm.core.components.models.Separator`

### Usages in *.html files
- **Data-sly-use declaration**: `<div data-sly-use.separator="com.adobe.cq.wcm.core.components.models.Separator">`
- **Expressions where the model is used**:
  - `id="${component.id}"`
  - `data-sly-attribute.role="${separator.decorative ? 'none': ''}"`
  - `data-sly-attribute.aria-hidden="${separator.decorative ? 'true': ''}"`

### Table of Model Members

| JCR Attribute | Java Property | Description                                                                 |
|---------------|---------------|-----------------------------------------------------------------------------|
| ./id          | id            | Defines the component HTML ID attribute.                                    |
| ./isDecorative| decorative    | If set to `true`, then the separator will be ignored by assistive technology|
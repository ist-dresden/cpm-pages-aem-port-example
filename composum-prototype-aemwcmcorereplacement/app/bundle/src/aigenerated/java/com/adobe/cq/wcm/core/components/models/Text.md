<!-- AIGenVersion(506b8f147e, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.3, README.md-822d35f834, text.html-c5e39866f6) -->

Specification for `com.adobe.cq.wcm.core.components.models.Text`
====
Text component written in HTL that provides a section of rich text.

### Classname
`com.adobe.cq.wcm.core.components.models.Text`

### Usages in *.html files
- `data-sly-use.textModel="com.adobe.cq.wcm.core.components.models.Text"`
- `${textModel.text}`
- `${textModel.isRichText}`
- `${textModel.data.json}`
- `${component.id}`

### Table of Model Members

| JCR Attribute | Java Property | Description                                                                 |
|---------------|---------------|-----------------------------------------------------------------------------|
| ./text        | text          | the actual text to be rendered                                              |
| ./textIsRich  | isRichText    | flag determining if the rendered text is rich or not                        |
| ./id          | id            | defines the component HTML ID attribute.                                    |
|               | data.json     | used for data layer integration, not directly tied to a JCR attribute      |

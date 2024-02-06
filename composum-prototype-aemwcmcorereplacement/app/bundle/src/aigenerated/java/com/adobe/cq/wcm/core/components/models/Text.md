<!-- AIGenVersion(2992911859, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.4, README.md-822d35f834, text.html-146972031c, text.html-c5e39866f6) -->

Specification for `com.adobe.cq.wcm.core.components.models.Text`
====
Text component written in HTL that provides a section of rich text.

### Classname
`com.adobe.cq.wcm.core.components.models.Text`

### Usages in *.html files
- `data-sly-use.textModel="com.adobe.cq.wcm.core.components.models.Text"`
- `<p data-sly-test.text="${textModel.text}" data-sly-unwrap="${textModel.isRichText}">${text @ context = textModel.isRichText ? 'html' : 'text'}</p>`
- `<div data-sly-use.textModel="com.adobe.cq.wcm.core.components.models.Text"`
- `<p class="cmp-text__paragraph" data-sly-unwrap="${textModel.isRichText}">${text @ context = textModel.isRichText ? 'html' : 'text'}</p>`
- `data-cmp-data-layer="${textModel.data.json}"`
- `id="${component.id}"`

### Table for `com.adobe.cq.wcm.core.components.models.Text`

| JCR Attribute | Java Property | Data Type | Description                                                                 |
|---------------|---------------|-----------|-----------------------------------------------------------------------------|
| ./text        | text          | String    | the actual text to be rendered                                              |
| ./textIsRich  | isRichText    | Boolean   | flag determining if the rendered text is rich or not                        |
| ./id          | id            | String    | defines the component HTML ID attribute.                                    |
|               | data          | Object    | Object for data layer integration, not directly tied to a JCR attribute.    |

### Table for `data` object in `com.adobe.cq.wcm.core.components.models.Text`

| JCR Attribute | Java Property | Data Type | Description                                   |
|---------------|---------------|-----------|-----------------------------------------------|
|               | json          | String    | JSON string for data layer integration.       |
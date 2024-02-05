<!-- AIGenVersion(387806aeab, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.2, README.md-822d35f834, text.html-c5e39866f6) -->

### Short Description from README.md
Text component written in HTL that provides a section of rich text.

### Classname listed in '### Use Object' in the README.md
`com.adobe.cq.wcm.core.components.models.Text`

### Usages of models with that class in the *.html files
- `data-sly-use.textModel="com.adobe.cq.wcm.core.components.models.Text"`
- `data-sly-test.text="${textModel.text}"`
- `data-cmp-data-layer="${textModel.data.json}"`
- `id="${component.id}"`
- `data-sly-unwrap="${textModel.isRichText}"`
- `${text @ context = textModel.isRichText ? 'html' : 'text'}`

### Table of Model Members

| JCR Attribute | Java Property | Description                                                                                   |
|---------------|---------------|-----------------------------------------------------------------------------------------------|
| `./text`      | `text`        | The actual text to be rendered.                                                               |
| `./textIsRich`| `isRichText`  | Flag determining if the rendered text is rich or not, useful for applying the correct HTL display context. |
| `./id`        | `id`          | Defines the component HTML ID attribute.                                                      |
|               | `data.json`   | Property used for data layer functionality, not directly tied to a JCR attribute.             |

This table includes all the members of the `com.adobe.cq.wcm.core.components.models.Text` model that are used in the HTML file, ensuring the model supports all functionalities demonstrated, including content rendering and integration with data layers.

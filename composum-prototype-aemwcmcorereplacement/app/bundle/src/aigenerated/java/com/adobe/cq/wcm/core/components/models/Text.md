<!-- AIGenVersion(ee6d9930ad, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.2, README.md-7c5fdf2c99, text.html-f02476e7ef) -->

# Apache Sling Model Specification for `com.adobe.cq.wcm.core.components.models.Text`

## Short Description
Text component written in HTL that provides a section of rich text.

## Classname
The model uses the `com.adobe.cq.wcm.core.components.models.Text` Sling model as its Use-object.

## Usages in *.html Files
In the provided `text.html` file, the model `com.adobe.cq.wcm.core.components.models.Text` is used with the following expressions:
- `data-sly-use.textModel="com.adobe.cq.wcm.core.components.models.Text"`
- `data-sly-test.text="${textModel.text}"`
- `id="${component.id}"`
- `data-sly-unwrap="${textModel.isRichText}"`
- `${text @ context = textModel.isRichText ? 'html' : 'text'}`

## Table of Model Members

| JCR Attribute | Java Property | Description                                                                 |
|---------------|---------------|-----------------------------------------------------------------------------|
| `./text`      | text          | The actual text to be rendered.                                             |
| `./textIsRich`| isRichText    | Flag determining if the rendered text is rich or not.                       |
| `./id`        | id            | Defines the component HTML ID attribute. This is used but not in a JCR property directly in the HTML file, but it's implied to be part of the `com.adobe.cq.wcm.core.components.models.Component` model which is also used in the HTML file. |

Note: The `id` property is utilized in the HTML file but it is associated with the `com.adobe.cq.wcm.core.components.models.Component` model, not directly with the `Text` model. However, it's important to ensure that the `Text` model can work seamlessly with the `Component` model, hence its inclusion in the usage context.
<!-- AIGenVersion(bdf1a146eb, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.0, README.md-7c5fdf2c99, text.html-f02476e7ef) -->

Text (v2)
====
Text component written in HTL that provides a section of rich text.

### Use Object
The Text component uses the `com.adobe.cq.wcm.core.components.models.Text` Sling model as its Use-object.

| JCR Attribute | Java Property | Description                                                                 |
|---------------|---------------|-----------------------------------------------------------------------------|
| ./text        | text          | the actual text to be rendered                                              |
| ./textIsRich  | isRichText    | flag determining if the rendered text is rich or not, useful for applying the correct HTL display context |
| ./id          | id            | defines the component HTML ID attribute.                                    |
<!-- AIGenVersion(1.2) -->
Consider the retrieved README.md and the retrieved Sightly / HTL *.html files for information about the Apache Sling
model `com.adobe.cq.wcm.core.components.models.Text`. Generate a specification for the code this model needs to be
created. Look for the `data-sly-use` declaration of a Sling model with
class `com.adobe.cq.wcm.core.components.models.Text`, and usages of these declared models in expressions in the *.html
files, including properties related to data layers, analytics, or any other functionalities not directly tied to JCR
attributes.

Describe in a table all members of the model that need to be declared in the code of the model so that the *.html
files compile properly. Include all members of the model that are used in the HTML files, not only those
tied to JCR attributes but also those utilized for advanced features or integrations such as data layers, analytics,
etc. This is crucial for a complete specification, as it ensures the model supports all functionalities demonstrated in
the HTML files, including but not limited to content rendering.

In the retrieved README.md, the section `### Use Object` contains the JCR attributes that need to be read by the class
model later. If the members of `com.adobe.cq.wcm.core.components.models.Text` match any of these attributes, then denote
that in the table. Additionally, include any properties used for functionalities like data layers, analytics, etc., even
if they are not directly tied to a JCR attribute.

Output a Markdown document with the following information:

1. The short description from the README.md
2. The classname listed in '### Use Object' in the README.md
3. Output all usages of models with that class in the *.html files.
4. A table with the following columns:
    - `jcr attribute` : the JCR attribute that is returned by the java property, as listed in README.md
    - `java property` : the Java property name as determined from usage as Sling Models model the .html files.
      IMPORTANT: only when it is actually used in a .html file!
    - `description` : the description of the jcr attribute, as listed in the README.

IMPORTANT: include all members of the model that are used in the HTML, even if they are not tied to a JCR attribute,
since we'll otherwise get compilation errors!

Example for the table, where bar is not used in a Java property of the Sling Model and id is used but not in a JCR
property:

| JCR Attribute | Java Property | Description                                                     |
|---------------|---------------|-----------------------------------------------------------------|
| foo           | foo           | a foo to be used as thingamagic                                 |
| bar           |               | flag for the type of the foo which is not used in a .html       |
|               | id            | generated id used in a .html but not mentioned in the README.md |

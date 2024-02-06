<!-- AIGenVersion(1.4) -->
Consider the retrieved README.md and the retrieved Sightly / HTL *.html files for information about the Apache Sling
model `MODELCLASS`. Generate a specification for the code this model needs to be
created. Look for the `data-sly-use` declaration of a Sling model with
class `MODELCLASS`, and usages of these declared models in expressions in the *.html
files, including properties related to data layers, analytics, or any other functionalities not directly tied to JCR
attributes.

Describe in a table all members of the model that need to be declared in the code of the model so that the *.html
files compile properly. Include all members of the model that are used in the HTML files, not only those
tied to JCR attributes but also those utilized for advanced features or integrations such as data layers, analytics,
etc. This is crucial for a complete specification, as it ensures the model supports all functionalities demonstrated in
the HTML files, including but not limited to content rendering.

In the retrieved README.md, the section `### Use Object` contains the JCR attributes that need to be read by the class
model later. If the members of `MODELCLASS` match any of these attributes, then denote
that in the table. Additionally, include any properties used for functionalities like data layers, analytics, etc., even
if they are not directly tied to a JCR attribute.

Output a Markdown document with the following information:

1. The headline "Specification for `MODELCLASS`"
2. The short description from the README.md
3. The classname listed in '### Use Object' in the README.md
4. Output all usages of models with that class in the *.html files - the `data-sly-use` declaration and all 
   lines of the *.html files where the model is used in expressions - including the whole HTML tag.
5. A table with the following columns:
    - `JCR Attribute` : the JCR attribute that is returned by the java property, as listed in README.md
    - `Java Property` : the Java property name as determined from usage as Sling Models model the .html files.
      IMPORTANT: only when it is actually used in a .html file!
    - `Data Type` : the Java data type of the jcr attribute, as it fits the information in the README and the usage 
      in the *.html files.
    - `Description` : the description of the jcr attribute, as listed in the README.

If, judging from the *.html*, a Java property returns an object, do include it into the usages and create another 
table for it, with the same columns as above. E.g. `data-sly-list.navItem="${breadcrumb.items} ... ${navItem.title}` 
indicates that `items` is a list of objects, and `navItem` is an object with a `title` attribute. The objects should 
be an inner class of the model class, except for `.data.json`.

IMPORTANT: include all members of the model that are used in the HTML, even if they are not tied to a JCR attribute,
since we'll otherwise get compilation errors!

Example for the table, where bar is not used in a Java property of the Sling Model and id is used but not in a JCR
property:

| JCR Attribute | Java Property | Data Type | Description                                                     |
|---------------|---------------|-----------|-----------------------------------------------------------------|
| foo           | foo           | String    | a foo to be used as thingamagic                                 |
| bar           |               | Boolean   | flag for the type of the foo which is not used in a .html       |
|               | id            | String    | generated id used in a .html but not mentioned in the README.md |

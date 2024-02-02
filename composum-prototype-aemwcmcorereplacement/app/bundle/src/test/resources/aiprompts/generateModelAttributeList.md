Consider the given README.md and the Sightly / HTL .html files for information about the MODELCLASS .
In the README.md the section `### Use Object` contains the JCR attributes that need to be read by the class
MODELCLASS later, and the .html file can show you which java property will be used
to access those attributes.

Output a markdown document with the following information:

1. The short description from the README.md
2. The classname listed in '### Use Object' in the README.md
3. A table with the following columns:
    - `jcr attribute` : the JCR attribute that is returned by the java property, as listed in README.md
    - `java property` : the Java property name as determined from usage as Sling Models model the .html files. 
      IMPORTANT: only when it is actually used in a .html file!
    - `description` : the description of the jcr attribute, as listed in the README.

Example for the table, where bar is not used in a Java property of the Sling Model and id is used but not in a JCR 
property:  

| JCR Attribute | Java Property | Description                     |
|-------------|---------------|---------------------------------|
| foo         | foo           | a foo to be used as thingamagic |
| bar         |               | flag for the type of the foo    |
|             | id            | generated id                    |

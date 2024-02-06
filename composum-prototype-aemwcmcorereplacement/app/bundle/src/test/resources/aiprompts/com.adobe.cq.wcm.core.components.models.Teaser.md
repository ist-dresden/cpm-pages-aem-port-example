<!-- AIGenVersion(1.1) -->

# Additional specification for com.adobe.cq.wcm.core.components.models.Teaser

In the specification the Action class should be included that maps the `./actions/*` child nodes. There should
be a table with it's properties, and usages of it's properties in the HTML files.

The following code automatically maps the child nodes of the `./actions` node to a `List` of `Action` objects:

```java

@ChildResource
private List<Action> actions;


public List<Action> getActions() {
    return actions;
}
```

Define the Action class according to the attributes declared for the actions/items nodes in the README.md and the
HTML files. The "Action Class Specification" table should contains it's attributes.

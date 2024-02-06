<!-- AIGenVersion(1.4) -->
Create a POJO class MODELCLASS using Apache Sling Models that provide the properties discussed in the specification
file.

It should inherit from the class `AbstractComponent` and have exactly the annotation
`@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)`

It should provide the members discussed in the specification file as attributes and their respective getters.
The attributes mentioned in the JCR file should be annotated with `@ValueMapValue`.

The members that do not have a corresponding JCR attribute and are not explained enough in the README.md to 
have a clear implementation should just have a getter returning `null` or - if it's 
datatype is a collection - return an empty collection, and (only then!) put a TODO comment there. If you can 
conclude from the *.html files that a member returns an object collection, define an inner class for those objects,
except for `.data.json`
Observe any additional specifications given for the MODELCLASS .

The generated class should satisfy it's usages in the retrieved *.html, which use Apache Sighly / HTL.
Generate Javadoc comments.
Imports contain:

```
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
```

IMPORTANT: output only compileable Java source code - the actual java class, without codeblock marker, without any
introduction and discussion. The output has to be entirely compileable for the Java compiler just as it is! 

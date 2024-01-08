# Details for the migration of the wcm.core.components to Composum Pages components

The WKND components are mostly thin wrappers around the wcm.core.components, so we can refer to
[their documentation](https://github.com/adobe/aem-core-wcm-components)
to see what needs to be migrated.

FIXME: generally what to do about cq:styleIds ?

## General migration

In all components we need to replace the sling:resourceType with the corresponding Composum Pages component.

## Text component

wknd/components/text -> core/wcm/components/text/v2/text
replaced by
composum/prototype/aem-wcm-core-replacement/components/text ->
composum/pages/components/element/text

https://github.com/ist-dresden/composum-pages/tree/develop/components/package/src/main/content/jcr_root/libs/composum/pages/components/element/text
vs.
https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/text/v2/text

The AEM component has an attribute text that can be kept as is if textIsRich is set to true. Otherwise we need to
convert the text to HTML, as there is no such equivalent at Pages.

## Title component

wknd/components/title -> core/wcm/components/title/v3/title
replaced by
composum/prototype/aem-wcm-core-replacement/components/parsys ->
composum/pages/components/element/title

https://github.com/ist-dresden/composum-pages/tree/develop/components/package/src/main/content/jcr_root/libs/composum/pages/components/element/title
vs.
https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/title/v3/title

Composum: properties title, subtitle, image
AEM:
./jcr:title - will store the text of the title to be rendered
./type - will store the HTML heading element type which will be used for rendering; if no value is defined, the
component will fallback to the value defined by the component's policy
./linkURL - will allow definition of a content page path, external URL or page anchor for linking the title.
./id - defines the component HTML ID attribute.
./linkAccessibilityLabel - defines an accessibility label for the the title's link.
./linkTitleAttribute - defines a title attribute for the the title's link.

Pages extension?

Unsolved problem: refers to component policy for fallback.

## Container component

wknd/components/container -> core/wcm/components/container/v1/container
replaced by
composum/prototype/aem-wcm-core-replacement/components/parsys ->
composum/pages/components/container/parsys

https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/container/v1/container
vs.
https://github.com/ist-dresden/composum-pages/tree/develop/components/package/src/main/content/jcr_root/libs/composum/pages/components/container/parsys

Composum: no properties. Layout normally with rows and columns
AEM properties:
./layout - defines the layout type, either simple (default) or responsiveGrid; if no value is defined, the component
will fallback to the value defined by the component's policy
Common Properties
./backgroundImageReference - defines the container background image.
./backgroundColor - defines the container background color.
./id - defines the component HTML ID attribute.
Accessibility
./accessibilityLabel - defines an accessibility label for the container.
./roleAttribute - defines a role attribute for the container.

Unsolved problem: refers to component policy for fallback.

## Experience Fragment

wknd/components/experiencefragment -> core/wcm/components/experiencefragment/v1/experiencefragment
replaced by
composum/prototype/aem-wcm-core-replacement/components/experiencefragment -> composum/pages/components/element/reference

AEM Properties:
./fragmentVariationPath - defines the path to the experience fragment variation to be rendered.
./id - defines the component HTML ID attribute.

Composum: contentReference, but that normally refers to a component in a page, not a page itself. Thus, we put the jcr:
content/root of the experience fragment into the contentReference.

https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/experiencefragment/v2/experiencefragment
vs.
https://github.com/ist-dresden/composum-pages/tree/develop/components/package/src/main/content/jcr_root/libs/composum/pages/components/container/parsys

## Experience Fragment variation

wknd/components/xfpage -> cq/experience-fragments/components/xfpage -> wcm/foundation/components/page
replaced by
composum/prototype/aem-wcm-core-replacement/components/xfpage -> composum/pages/components/page/home

Special page type to edit experience fragments. Not used in rendering.

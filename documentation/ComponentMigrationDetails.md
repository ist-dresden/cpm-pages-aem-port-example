# Details for the migration of the wcm.core.components to Composum Pages components

The WKND components are mostly thin wrappers around the wcm.core.components, so we can refer to
[their documentation](https://github.com/adobe/aem-core-wcm-components)
to see what needs to be migrated.

## General migration

In all components we need to replace the sling:resourceType with the corresponding Composum Pages component.

## Text component

wknd/components/text -> composum/pages/components/element/text

https://github.com/ist-dresden/composum-pages/tree/develop/components/package/src/main/content/jcr_root/libs/composum/pages/components/element/text
vs.
https://github.com/adobe/aem-core-wcm-components/tree/main/content/src/content/jcr_root/apps/core/wcm/components/text/v2/text

The AEM component has an attribute text that can be kept as is if textIsRich is set to true. Otherwise we need to
convert the text to HTML, as there is no such equivalent at Pages.

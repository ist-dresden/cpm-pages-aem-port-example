# Substitution / port of the AEM wcm core components

It is approach for the migration. We try to make an AEM project run on Composum Pages with as few modification as
possible. We try to make sure that HTL pages run on composum with no changes if possible, instead of using JSPs.
Only very convoluted functionalities of AEM that have a large mismatch to Composum should be migrated.

## Differences of Composum and AEM – areas to migrate

- Primary types for JCR
- Components:
    - Dialogs
- Page templates
- Site configuration

## Handling of primary types

Composum has pages as cpp:Page / cpp:PageContent, and the site being cpp:Site (whereas in AEM there is no such
concept - the site root is cq:Page). Also, the components in the page have primary types
cpp:Component, cpp:Container, cpp:Element . It's not quite clear whether it's necessary to use them - we'll see whether
we actually need to migrate that.

cq:Page is very similar to cpp:Page - no additional attributes, so we can have cq:Page inherit from cpp:Page .

cq:PageContent has much internal structure, while cpp:PageContent has not:

```

[cq:PageContent] > cq:OwnerTaggable, cq:ReplicationStatus, mix:created, mix:title, nt:unstructured, sling:Resource, sling:VanityPath
  orderable
  - pageTitle (string)
  - cq:lastModified (date)
  - jcr:language (string)
  - offTime (date)
  - cq:lastModifiedBy (string)
  - hideInNav (boolean)
  - cq:designPath (string)
  - cq:template (string)
  - onTime (date)
  - navTitle (string)
  - cq:allowedTemplates (string) multiple

[cpp:PageContent] > mix:created, mix:lastModified, mix:lockable, mix:versionable, nt:unstructured
  orderable
```

The attributes all, however, all optional. So it does not really hurt to have cq:PageContent inherit from cpp:
PageContent.

As a first try, we change the inheritance structure of cq:Page and cq:PageContent and change the site roots to cpp:Site,
and will see whether this creates trouble.

## General migration approach for components

At the beginning we create a substitution component type wknd/components/unmigrated that just displays basic data about
the resource and it's children. For all components used in the site we create stubs that point to that as supertype,
and will step by step replace them with actual components.

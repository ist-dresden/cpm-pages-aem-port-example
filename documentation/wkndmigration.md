# Partial Migration of the AEM WKND project to Composum Pages

This document describes the steps taken to migrate the [AEM WKND project](https://github.com/adobe/aem-guides-wknd) to
Composum Pages.

## Import content

1. Create package with /content/wknd, /content/dam/wknd, /content/experience-fragments/wknd, /conf/wknd to import into a
   Composum Pages test instance
2. Import that package into a Composum Pages test instance. Note: the additional AEM nodetypes are automatically
   registered by the package import.

## (Analysis of component structure)[(https://stoerr.github.io/til/AdobeAEM/AnalyzeSite.html)]

see wkndresourcetypes.txt and wkndattributes.txt

## Migration of primary types

The following primary types correspond, and can be migrated by the
[MigratePrimaryTypes](../composum-prototype-aemwcmcorereplacement/app/package/src/main/content/jcr_root/apps/composum/prototype/aem-wcm-core-replacement/groovy/MigratePrimaryTypes.groovy)
script. A Sling quirk is that you have to do an addMixin for mix:versionable before setting cpp:PageContent since
setting the primary type doesn't add the necessary attributes for versioning (cpp:PageContent is versionable, cq not).

* cq:Page -> cpp:Page
* cq:PageContent -> cpp:PageContent

## Migration of components

We will create components that are compatible to the AEM WKND components, but use the Composum Pages components as
a basis.
One of the main problems is that the AEM components have different attributes and structure
than the Composum Pages components.
That can be solved two ways:

1. either we do a migration - possibly together with the sling:resourceType and substructure
2. or we create new components that are compatible to the AEM WKND components, but use the Composum Pages components as
   a basis.

Doing a migration requires a migration framework and is thus not quite easy, and testing is more difficult since we have
to switch back and forth between the original source and the changed source - except if we copy everything to a new
location. OTOH the result then has the same structure as the
normal Composum site, whereas I see no good way to use the corresponding Composum component directly since the model
requires a certain structure. Possibly it'd be possible to generate synthetic resources on the fly from the AEM
structure (a bit similar to what the Composum dashboard does in some places), but that's a bit of a hack.

One way to do the migration so that it can easily repeat it again and again would be to create a copy script that copies
a part of the JCR tree (such as a subsite of WKND) to a new location, rewrites references while it's at it, and then
do the migration on that copy. This has the advantages of a migration while being easily repeatable. When the migration
is ready, we can perform the migration on the original tree, instead.

### Component equivalences

wknd/components/page -> composum/pages/components/page/home
wknd/components/container -> composum/pages/components/container/parsys
wknd/components/title -> composum/pages/components/element/title
wknd/components/text -> composum/pages/components/element/text
wknd/components/image -> composum/pages/components/element/image
wknd/components/experiencefragment -> new component? reference?
wknd/components/contentfragment -> new component? reference?
wknd/components/xfpage -> new component that just renders the root of the experience fragment

## Unsolved problems

- Generate documentation for the pages components
- Generate documentation for the WKND components
- Automatic migration?
- Are the jcr:uuid attributes referenced? If so, how to migrate them? 

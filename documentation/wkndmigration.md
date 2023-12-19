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

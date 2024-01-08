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

FIXME: migrate cq:responsive child node.

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

## Image

wknd/components/image -> core/wcm/components/image/v3/image
replaced by
composum/prototype/aem-wcm-core-replacement/components/image -> composum/pages/components/element/image

Composum properties: imageRef, title, alt, copyright, copyrightUrl, license, licenseUrl

AEM properties:
./fileReference property or file child node - will store either a reference to the image file, or the image file
./isDecorative - if set to true, then the image will be ignored by assistive technology
./alt - defines the value of the HTML alt attribute (not needed if ./isDecorative is set to true)
./altValueFromDAM - if true, the HTML alt attribute is inherited from the DAM asset.
./linkURL - allows defining a URL to which the image will link to
./jcr:title - defines the value of the HTML title attribute or the value of the caption, depending on the value of
./displayPopupTitle - if set to true it will render the value of the ./jcr:title property through the HTML title
attribute, otherwise a caption will be rendered
./id - defines the component HTML ID attribute.
./dmPresetType - defines the type of Dynamic Media image rendering, possible values are imagePreset, smartCrop.
./imagePreset - defines the name for the Dynamic Media Image Preset to apply to the Dynamic Media image URL.
./smartCropRendition - defines how Dynamic Media Smart Crop image renders. SmartCrop:Auto means that the component will
automatically select Smart Crop rendition which fits the container size better; the name of specific Smart Crop
rendition will force the component to render that image rendition only.
./imageModifiers - defines additional Dynamic Media Image Serving commands separated by '&'. Field gives complete
flexibility to change Dynamic Media image rendering.
./imageFromPageImage - if true, the image is inherited from the featured image of either the linked page if ./linkURL is
set or the current page.
./altValueFromPageImage - if true and if ./imageFromPageImage is true, the HTML alt attribute is inherited from the
featured image of either the linked page if ./linkURL is set or the current page.
./disableLazyLoading - if true the lazy loading of the image is disabled regardless of the lazy loading setting in the
design policy.

Really used in WKND: fileReference (100%), altValueFromDAM (60%), displayPopupTitle (60%), isDecorative (60%),
titleValueFromDAM (60%), imageCrop (9%), jcr:title (5%), alt (3%), cq:panelTitle (2%)

Shortcut for now: migrate fileReference to the jcr:content/renditions/original , 
alt stays as it is, if displayPopupTitle is true, migrate jcr:title as alt, otherwise as title.

FIXME: migrate using assets. But first migrate as picture to get it running quick.
Unsolved problem: refers to component policy for fallback.

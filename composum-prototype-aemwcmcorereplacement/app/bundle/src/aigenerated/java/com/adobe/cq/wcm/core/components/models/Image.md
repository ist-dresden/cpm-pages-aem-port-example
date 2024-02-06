<!-- AIGenVersion(01f5b7d659, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.4, README.md-c085f77001, image.html-e696a7417b, image.html-a1cb9b0eac, com.adobe.cq.wcm.core.components.models.Image.md-47c94eec24) -->

Specification for `com.adobe.cq.wcm.core.components.models.Image`
====
Image component written in HTL that renders an adaptive image.

### Classname
com.adobe.cq.wcm.core.components.models.Image

### Usages in *.html files
- `data-sly-use.image="com.adobe.cq.wcm.core.components.models.Image"`
- `<div data-sly-use.image="com.adobe.cq.wcm.core.components.models.Image"`
- `<a data-sly-unwrap="${!image.link}"`
- `<noscript data-sly-unwrap="${!image.json}" data-cmp-image="${image.json}">`
- `<img src="${image.src}"`
- `<span class="cmp-image--title" data-sly-test="${!image.displayPopupTitle && image.title}">${image.title}</span>`
- `<div data-sly-use.image="com.adobe.cq.wcm.core.components.models.Image"`
- `<a data-sly-unwrap="${!image.link}"`
- `<noscript data-sly-unwrap="${image.smartCropRendition != 'SmartCrop:Auto' && (!image.lazyEnabled && image.widths.length <= 1 && !image.areas)}" data-cmp-hook-image="noscript">`
- `<img src="${image.src}" class="cmp-image__image"`
- `<span class="cmp-image__title" itemprop="caption" data-sly-test="${!image.displayPopupTitle && image.title}">${image.title}</span>`
- `<meta itemprop="caption" content="${image.title}" data-sly-test="${image.displayPopupTitle && image.title}">`

### Table for `com.adobe.cq.wcm.core.components.models.Image`

| JCR Attribute          | Java Property         | Data Type | Description                                                                                   |
|------------------------|-----------------------|-----------|-----------------------------------------------------------------------------------------------|
| ./fileReference        | fileReference         | String    | A reference to the image file.                                                                |
| ./alt                  | alt                   | String    | Defines the value of the HTML `alt` attribute.                                                |
| ./linkURL              | link                  | String    | Allows defining a URL to which the image will link to.                                        |
| ./jcr:title            | title                 | String    | Defines the value of the HTML `title` attribute or the value of the caption.                  |
| ./displayPopupTitle    | displayPopupTitle    | Boolean   | Determines if the title is displayed as a tooltip or as a caption.                            |
| ./id                   | id                    | String    | Defines the component HTML ID attribute.                                                      |
|                        | src                   | String    | The image source URL.                                                                         |
|                        | json                  | String    | JSON string representing image properties for data layer integration.                         |
|                        | lazyEnabled           | Boolean   | Indicates if lazy loading is enabled.                                                         |
|                        | lazyThreshold         | Integer   | Defines the number of pixels an image is loaded before it becomes visible.                    |
|                        | srcUriTemplate        | String    | URI template for generating image URLs with variable width.                                   |
|                        | widths                | int[]     | Array of allowed rendition widths.                                                            |
|                        | dmImage               | Boolean   | Indicates if the image is a Dynamic Media image.                                              |
|                        | smartCropRendition    | String    | Defines how Dynamic Media Smart Crop image renders.                                           |
|                        | fileReference         | String    | A reference to the image file, used for data layer and analytics.                             |
|                        | uuid                  | String    | A unique identifier for the image, used for data layer and analytics.                         |
|                        | data                  | Object    | An object containing data layer information.                                                  |

Note: The `data` object mentioned in the table would require its own specification if it contains properties used within the HTML files, which are not explicitly listed here due to the lack of detailed usage in the provided snippets.
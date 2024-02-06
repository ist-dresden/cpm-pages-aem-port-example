<!-- AIGenVersion(ff886928c1, generalsystemmessage.prompt-1.0, generateModelAttributeList.md-1.4, README.md-c465cd262a, breadcrumb.html-9300cfe3ac, breadcrumb.html-f4c7ffeb4a) -->

Specification for `com.adobe.cq.wcm.core.components.models.Breadcrumb`
----
Breadcrumb component written in HTL.

### Classname
`com.adobe.cq.wcm.core.components.models.Breadcrumb`

### Usages in *.html files
- `data-sly-use.breadcrumb="com.adobe.cq.wcm.core.components.models.Breadcrumb"`
- `<nav data-sly-use.breadcrumb="com.adobe.cq.wcm.core.components.models.Breadcrumb"`
- `data-sly-list.navItem="${breadcrumb.items}"`
- `id="${component.id}"`
- `data-cmp-data-layer="${breadcrumb.data.json}"`
- `data-cmp-data-layer="${navItem.data.json}"`

### Table for `com.adobe.cq.wcm.core.components.models.Breadcrumb`

| JCR Attribute | Java Property | Data Type | Description                                                     |
|---------------|---------------|-----------|-----------------------------------------------------------------|
| ./startLevel  | startLevel    | Integer   | the level at which to start the breadcrumb: 0 = /content, 1 = /content/site, etc. |
| ./showHidden  | showHidden    | Boolean   | if `true`, show navigation items hidden via a ./hideInNav property in the breadcrumb. |
| ./hideCurrent | hideCurrent   | Boolean   | if `true`, don't display the current page in the breadcrumb.    |
| ./disableShadowing | disableShadowing | Boolean | for redirecting pages PageA -> PageB. If `true` - PageA(original page) is shown. If `false` or not configured - PageB(target page). |
|               | items         | List      | A list of breadcrumb items. Each item is an object with properties used in the breadcrumb rendering. |
|               | id            | String    | The component HTML ID attribute.                                |
|               | data          | Object    | An object for data layer integration, containing a `json` property. |

### Table for items in `com.adobe.cq.wcm.core.components.models.Breadcrumb`

| JCR Attribute | Java Property | Data Type | Description                                                     |
|---------------|---------------|-----------|-----------------------------------------------------------------|
|               | title         | String    | The title of the breadcrumb item.                               |
|               | URL           | String    | The URL of the breadcrumb item.                                 |
|               | active        | Boolean   | Indicates if the breadcrumb item is the current page.           |
|               | data          | Object    | An object for data layer integration, containing a `json` property for the breadcrumb item. |
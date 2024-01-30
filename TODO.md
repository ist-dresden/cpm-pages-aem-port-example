# TODO for Composify WKND

## General Todos

- Migrate images to Composum Assets

## Components to migrate

wknd/components/container	499
wknd/components/image	333
wknd/components/title	242
wknd/components/contentfragment	214
dam/cfm/components/grid	268
wcm/foundation/components/responsivegrid	78
(wknd/components/sharing	70)
wknd/components/text	60
wknd/components/carousel	52
wknd/components/tabs	52
wknd/components/breadcrumb	49
wknd/components/experiencefragment	46
wknd/components/teaser	42
wknd/components/separator	43
wknd/components/page	123
wknd/components/image-list	30
wknd/components/list	22
wknd/components/button	7
wknd/components/download	6
wknd/components/accordion	3
wknd/components/embed	1
wknd/components/byline	1

other stuff from experience fragments:

wknd/components/xfpage	35
cq/experience-fragments/editor/components/buildingblock	19
wknd/components/form/button	3
wknd/components/form/hidden	3
wknd/components/form/sign-in-buttons	1
wknd/components/form/sign-in-form	3
wknd/components/languagenavigation	1
wknd/components/navigation	3
wknd/components/search	1

## Missing packages in deployment of WKND

com.adobe.cq.wcm.core.components.models [12.23.0,13.0.0)
com.adobe.cq.wcm.core.components.models.datalayer [1.3.0,2.0.0)
com.adobe.cq.wcm.core.components.models.datalayer.builder [1.2.0,2.0.0)
com.adobe.cq.wcm.core.components.util [1.4.0,2.0.0)
com.day.cq.search [1.4.0,2.0.0)
com.day.cq.search.result [1.2.0,2.0.0)
com.day.cq.wcm.api [1.29.0,2.0.0)
com.day.cq.wcm.api.components [5.8.0,6.0.0)

Package usage:
com.adobe.cq.wcm.core.components , com.day.cq : ImageList, ImageListImpl, BylineImpl, HelloWorldModel

Actually used: 

2 import com.day.cq.commons.jcr.JcrConstants;
1 import com.day.cq.search.Predicate;
1 import com.day.cq.search.PredicateConverter;
2 import com.day.cq.search.PredicateGroup;
1 import com.day.cq.search.Query;
2 import com.day.cq.search.QueryBuilder;
1 import com.day.cq.search.eval.JcrPropertyPredicateEvaluator;
1 import com.day.cq.search.eval.PathPredicateEvaluator;
1 import com.day.cq.search.eval.TypePredicateEvaluator;
1 import com.day.cq.search.result.SearchResult;
4 import com.day.cq.wcm.api.Page;
2 import com.day.cq.wcm.api.PageManager;
2 import com.day.cq.wcm.api.components.ComponentContext;

1 import com.adobe.cq.wcm.core.components.models.ListItem wrappedListItem;
1 import com.adobe.cq.wcm.core.components.models.List coreList;
1 import com.adobe.cq.wcm.core.components.internal.DataLayerConfig;
4 import com.adobe.cq.wcm.core.components.models.Image;
1 import com.adobe.cq.wcm.core.components.models.List;
1 import com.adobe.cq.wcm.core.components.models.ListItem;
3 import com.adobe.cq.wcm.core.components.models.datalayer.ComponentData;
1 import com.adobe.cq.wcm.core.components.models.datalayer.builder.DataLayerBuilder;
1 import com.adobe.cq.wcm.core.components.util.ComponentUtils;

in wcm.core.components there are about 70 AEM classes referenced - i.e. a full port is out of the question.

## AI, perhaps:

- automatical Documentation of Composum components - both as human readable as well as machine readable
- automatical Documentation of WKND components

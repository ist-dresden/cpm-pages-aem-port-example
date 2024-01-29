package com.adobe.cq.export.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ComponentExporter {

    @JsonProperty(":type")
    String getExportedType();

}

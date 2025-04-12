package org.dodo.text2sqlmcpserver.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
public class TableDescription {
    @JsonPropertyDescription("表名")
    private String table;
    @JsonPropertyDescription("表结构ddl")
    private String ddl;
}

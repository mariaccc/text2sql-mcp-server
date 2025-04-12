package org.dodo.text2sqlmcpserver.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
public class TableInfo {
    @JsonPropertyDescription("表名")
    private String tableName;
    @JsonPropertyDescription("表注释")
    private String tableComment;
}

package org.dodo.text2sqlmcpserver.domain.response;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import org.dodo.text2sqlmcpserver.domain.dto.TableDescription;
import java.util.List;

@Data
public class DatabaseResponse {
    @JsonPropertyDescription("数据库")
    private String scheme;
    @JsonPropertyDescription("查询需要的表和表结构ddl")
    private List<TableDescription> tables;
}

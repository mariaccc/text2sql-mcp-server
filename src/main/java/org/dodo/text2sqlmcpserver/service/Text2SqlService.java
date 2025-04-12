package org.dodo.text2sqlmcpserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dodo.text2sqlmcpserver.domain.dto.TableDescription;
import org.dodo.text2sqlmcpserver.domain.dto.TableInfo;
import org.dodo.text2sqlmcpserver.domain.response.DatabaseResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Text2SqlService {

    private final DatasourceService datasourceService;

    @Tool(description = "自然语言转SQL:步骤1:获取查询需要的表")
    public List<String> getNeededTables(@ToolParam(description = "数据库")String scheme,
                                        @ToolParam(description = "自然语言描述")String query) {
        log.debug("getNeededTables scheme = {}, query = {}", scheme, query);
        List<TableInfo> databaseTables = datasourceService.getDatabaseTables(scheme);
        return databaseTables.stream().map(TableInfo::getTableName).toList();
    }

    @Tool(description = "自然语言转SQL:步骤2:获取查询需要的表结构ddl")
    public DatabaseResponse getNeededTableDdl(@ToolParam(description = "数据库") String scheme,
                                              @ToolParam(description = "自然语言描述") String query,
                                              @ToolParam(description = "需要用到的表") List<String> tableList) {
        log.debug("getNeededTableDdl scheme = {}, query = {}", scheme, query);
        log.debug("tableList = {}", tableList);
        DatabaseResponse databaseResponse = new DatabaseResponse();
        databaseResponse.setScheme(scheme);
        List<TableDescription> tables = new ArrayList<>();
        tableList.forEach(tableInfo -> {
            TableDescription tableDdl = datasourceService.getTableDdl(scheme, tableInfo);
            tables.add(tableDdl);
        });
        databaseResponse.setTables(tables);
        log.debug("databaseResponse = {}", databaseResponse);
        return databaseResponse;
    }
}

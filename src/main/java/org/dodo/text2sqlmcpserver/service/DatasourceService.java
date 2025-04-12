package org.dodo.text2sqlmcpserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dodo.text2sqlmcpserver.domain.dto.TableDescription;
import org.dodo.text2sqlmcpserver.domain.dto.TableInfo;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatasourceService {

    private final JdbcClient jdbcClient;


    public List<TableInfo> getDatabaseTables(String scheme) {
        List<TableInfo> tableInfoList = jdbcClient.sql("SELECT TABLE_NAME as tableName, TABLE_COMMENT as tableComment FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?")
                .param(scheme)
                .query(TableInfo.class)
                .list();
        log.debug("scheme = {}, tableInfoList = {}", scheme, tableInfoList);
        return tableInfoList;
    }

    public TableDescription getTableDdl(String scheme, String tableName) {
        TableDescription tableDdl = jdbcClient.sql("SHOW CREATE TABLE `" + scheme + "`." + tableName)
                .query((rs, rowNum) -> {
                    TableDescription tableDescription = new TableDescription();
                    tableDescription.setTable(rs.getString("Table"));
                    tableDescription.setDdl(rs.getString("Create Table"));
                    return tableDescription;
                })
                .single();
        log.debug("scheme = {}, tableName = {}, tableDdl = {}", scheme, tableName, tableDdl);
        return tableDdl;
    }
}

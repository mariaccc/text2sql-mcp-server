# text2sql-mcp-server

## 使用
### 服务启动
修改`application.yml`，直接运行
```yaml
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true}
    username: ${SPRING_DATASOURCE_USERNAME:root}
    password: ${SPRING_DATASOURCE_PASSWORD:password}
```
或者修改`docker-compose.yaml`，使用`docker compose up --build -d`
```yaml
environment:
  SPRING_DATASOURCE_URL: "jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true"
  SPRING_DATASOURCE_USERNAME: "root"
  SPRING_DATASOURCE_PASSWORD: "password"
```
### 客户端配置
cherry-studio配置
```yaml
{
  "name": "text2sql-mcp-server",
  "type": "sse",
  "description": "",
  "isActive": true,
  "baseUrl": "http://localhost:8080/sse"
}
```
### 提示词模板
数据库：xxx
查询xxx
FROM eclipse-temurin:21
MAINTAINER dodo

WORKDIR /app

COPY ./target/text2sql-mcp-server-1.0.jar app.jar

EXPOSE 8080

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone

ENTRYPOINT ["java", "-jar", "-XX:+UseZGC", "-XX:+ZGenerational", "app.jar"]
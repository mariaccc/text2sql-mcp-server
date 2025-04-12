package org.dodo.text2sqlmcpserver.config;

import org.dodo.text2sqlmcpserver.service.Text2SqlService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {


    @Bean
    public ToolCallbackProvider toolCallbackProvider(Text2SqlService text2SqlService) {
        return MethodToolCallbackProvider.builder().toolObjects(text2SqlService).build();
    }
}

package com.ustc.oep.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author YuJianhua
 * @create 2023-03-18 18:50
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new ScreenShareWebSocketHandler(), "/ws").setAllowedOrigins("*");
        registry.addHandler(new ScreenShareWebSocketHandler(), "/ws").setAllowedOrigins("*");
    }
}

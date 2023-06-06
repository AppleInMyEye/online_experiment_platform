package com.ustc.oep.websocket;
/**
 * @author YuJianhua
 * @create 2023-03-18 18:51
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class ScreenShareWebSocketHandler extends TextWebSocketHandler {
    @Value("${online_experiment_platform.path}")
    private String bassPath;

    private static final int MAX_BinaryMessage_SIZE = 1024 * 1024 * 10;//10M

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("建立websocket连接");
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, session);
        session.setBinaryMessageSizeLimit(MAX_BinaryMessage_SIZE);
        session.sendMessage(new TextMessage("{\"type\":\"session_id\",\"session_id\":\"" + sessionId + "\"}"));
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        log.info("handlerBinaryMessage");
        byte[] payload =  message.getPayload().array();
        String sessionId = UUID.randomUUID().toString();
        File file = new File( bassPath + sessionId + ".webm");

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(payload);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message){
        log.info("handleTextMessage");
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        sessions.values().remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("websocket connection closed......closeStatus:" + closeStatus);
        sessions.values().remove(session);
        log.info("websocket connection closed......");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}

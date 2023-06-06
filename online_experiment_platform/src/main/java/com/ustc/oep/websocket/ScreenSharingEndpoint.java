//package com.ustc.oep.websocket;
//
///**
// * @author YuJianhua
// * @create 2023-03-18 16:53
// */
//
//
//import lombok.extern.slf4j.Slf4j;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//
//@Slf4j
//@ServerEndpoint("/screen-sharing/{id}")
//public class ScreenSharingEndpoint {
//
//    private static Map<String, Session> sessions = new HashMap<>();
//    private static Map<String, Session> screens = new HashMap<>();
//
//    @OnOpen
//    public void onOpen(Session session, @PathParam("id") String id) {
//        sessions.put(id, session);
//        log.info("建立连接: " + id);
//    }
//
//    @OnClose
//    public void onClose(Session session, @PathParam("id") String id) {
//        sessions.remove(id);
//        screens.remove(id);
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session, @PathParam("id") String id) throws IOException {
//        // 解析消息
//        JsonObject obj = JsonParser.parseString(message).getAsJsonObject();
//        String type = obj.get("type").getAsString();
//        JsonObject data = obj.get("data").getAsJsonObject();
//        log.info("收到消息: " + message);
//
//        if ("offer".equals(type)) {
//            // 发送offer消息到屏幕共享者
//            Session screen = screens.get(id);
//            if (screen != null) {
//                screen.getBasicRemote().sendText(message);
//            }
//        } else if ("answer".equals(type)) {
//            // 发送answer消息到学生
//            log.info("answer消息发送到学生");
//            Session student = sessions.get(id);
//            if (student != null) {
//                student.getBasicRemote().sendText(message);
//            }
//        } else if ("screen".equals(type)) {
//            // 学生请求屏幕共享
//            // 将学生和屏幕共享者进行匹配
//            String screenId = data.get("screenId").getAsString();
//            Session screen = sessions.get(screenId);
//            if (screen != null) {
//                screens.put(id, screen);
//                JsonObject response = new JsonObject();
//                response.addProperty("type", "screen");
//                response.addProperty("screenId", screenId);
//                session.getBasicRemote().sendText(response.toString());
//            }
//        } else if ("candidate".equals(type)) {
//            // 发送candidate消息到对方
//            Session destSession = screens.containsKey(id) ? screens.get(id) : sessions.get(id);
//            if (destSession != null) {
//                destSession.getBasicRemote().sendText(message);
//            }
//        }
//    }
//}

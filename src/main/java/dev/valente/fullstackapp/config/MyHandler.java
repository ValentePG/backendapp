package dev.valente.fullstackapp.config;

import com.google.gson.Gson;
import dev.valente.fullstackapp.model.ChatInput;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

public class MyHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        System.out.println(payload);
//        Gson gson = new Gson();
//        ChatInput user = gson.fromJson(payload, ChatInput.class);
        session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(payload)));
    }
}

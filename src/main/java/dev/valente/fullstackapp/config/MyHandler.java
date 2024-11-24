package dev.valente.fullstackapp.config;

import com.google.gson.Gson;
import dev.valente.fullstackapp.model.ChatInput;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyHandler extends TextWebSocketHandler {

    protected void handleTextMessage(WebSocketSession session, TextMessage message, HttpServletRequest request) throws IOException {
        String payload = message.getPayload();
        HttpHeaders httpHeaders = session.getHandshakeHeaders();
        HttpSession httpSession = request.getSession();
        InetSocketAddress address = session.getRemoteAddress();
        String idSesssion = session.getId();
        String ipUser = httpHeaders.getOrigin();



//        Gson gson = new Gson();
//        ChatInput user = gson.fromJson(payload, ChatInput.class);
        session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(payload)));
    }
}

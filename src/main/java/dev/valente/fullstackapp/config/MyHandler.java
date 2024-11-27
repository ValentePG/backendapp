package dev.valente.fullstackapp.config;

import com.google.gson.Gson;
import dev.valente.fullstackapp.model.ChatInput;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MyHandler extends AbstractWebSocketHandler {

    //UUID da sessão, precisa ser trocado por um UUID de Usuário
    private final Map<UUID, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //StandardWebSocketSession
        String payload = message.getPayload();
        Gson gson = new Gson();
        ChatInput user = gson.fromJson(payload, ChatInput.class);
        session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(user.user() + " : " + user.message())));

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        userSessions.put(UUID.fromString(session.getId()), session);
        System.out.println("Sessão com ID: " + session.getId() + " foi aberta e adicionada ao HashMap");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try(var teste = userSessions.remove(UUID.fromString(session.getId()))){
            System.out.println("Sessão com ID: " + session.getId() + " foi fechada e excluída do HashMap");
        } catch (Exception error){
            System.err.println(error.getMessage());
        }

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}

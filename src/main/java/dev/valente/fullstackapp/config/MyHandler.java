package dev.valente.fullstackapp.config;

import dev.valente.fullstackapp.model.ChatInput;
import dev.valente.fullstackapp.model.SessionInfoC;
import dev.valente.fullstackapp.singleton.UserSessionSingleton;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;


import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class MyHandler extends AbstractWebSocketHandler {

    //UUID da sessão, precisa ser trocado por um UUID de Usuário
    private final ConcurrentHashMap<UUID, SessionInfoC> userSessions = UserSessionSingleton.getInstance();

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //StandardWebSocketSession
        
        

        UUID currentSessionId = UUID.fromString(session.getId());
        String payload = message.getPayload();


        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ChatInput info = objectMapper.readValue(payload, ChatInput.class);
            userSessions.get(currentSessionId).setChatInput(info);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        TextMessage textMessage = new TextMessage(payload);

//        userSessions.forEach((id, wbs) -> {
//            if(wbs.getChatInput() != null){
//                System.out.println("Usuário: " + wbs.getChatInput().user() +
//                        "\nMensagem: " + wbs.getChatInput().message() +
//                        "\nID: " + wbs.getSession().getId());
//            }
//        });


        userSessions.forEach((id, wbs) -> {
            try {
                if(currentSessionId != id){
                    wbs.getSession().sendMessage(textMessage);
                }
            } catch (IOException e) {
                System.err.println("Erro sending messae: " + e.getMessage());
            }
        });


    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UUID id = UUID.fromString(session.getId());
        SessionInfoC sessionInfoC = new SessionInfoC(id, session);
        userSessions.put(id, sessionInfoC);
        System.out.println("Sessão com ID: " + session.getId() + " foi aberta e adicionada ao HashMap");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userSessions.remove(UUID.fromString(session.getId()))
                .getSession();
        System.out.println("Sessão com ID: " + session.getId() + " foi fechada e excluída do HashMap");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}

package dev.valente.fullstackapp.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.valente.fullstackapp.controller.SessionInfo;
import dev.valente.fullstackapp.singleton.UserSessionSingleton;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class MyHandler extends AbstractWebSocketHandler {

    //UUID da sessão, precisa ser trocado por um UUID de Usuário
    private final ConcurrentHashMap<UUID, SessionInfo> userSessions = UserSessionSingleton.getInstance();


//    private final OpenAiChatModel model = OpenAiChatModel.builder()
//            .apiKey("demo")
//            .modelName(GPT_4_O_MINI)
//            .build();
//        String AiResponse = model.generate(payload);
//
//
//        session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(AiResponse)));

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //StandardWebSocketSession


        var idSessaoAtual = UUID.fromString(session.getId());
        String payload = message.getPayload();

        userSessions.forEach((id, wbs) -> {
            try {
                if(idSessaoAtual != id){
                    wbs.webSocketSession().sendMessage(new TextMessage(HtmlUtils.htmlEscape(payload)));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpHeaders headers = session.getHandshakeHeaders();
        UUID id = UUID.fromString(session.getId());
        SessionInfo sessionInfo = new SessionInfo(headers.getOrigin(), session);

        userSessions.put(id, sessionInfo);
        System.out.println("Sessão com ID: " + session.getId() + " foi aberta e adicionada ao HashMap");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userSessions.remove(UUID.fromString(session.getId()))
                .webSocketSession();
        System.out.println("Sessão com ID: " + session.getId() + " foi fechada e excluída do HashMap");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}

package ir.ebcom.websocket.ws.controller;

import ir.ebcom.websocket.ws.model.UserSession;
import ir.ebcom.websocket.ws.service.UserService;
import ir.ebcom.websocket.ws.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@ServerEndpoint("/ws")
public class SocketEndpoint {
    private  UserService userService = UserServiceImpl.getInstance();



    //    private final UserRepository userRepository;

//    public SocketEndpoint(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @OnOpen
    public void onOpen(Session session) {
//        LOGGER.info("onOpen " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(message);
            } catch (Exception e) {
                onClose(session);
                return;
            }

//            if (jsonObject.has("action")) {
//                String action = jsonObject.get("action").toString();
//                switch (action) {
//                    case "token":
//                        //TODO must call esb api if success save on redis with time to live 120
//
//                        break;
//                    case "login":
//                        break;
//                    default:
//                        break;
//
//                }
//            }

            UserSession userSession = new UserSession("userId", session);
            userService.addSession(userSession);
            Optional<UserSession> userId = userService.getSession("userId");
            System.out.println(userId);
            session.getBasicRemote().sendText("Hi " + jsonObject.get("user") + " how may we help you?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
//        userRepository.removeUserId(session.getId());
    }

    @OnError
    public void onError(Throwable t) {
        log.error(t.getMessage());
    }
}

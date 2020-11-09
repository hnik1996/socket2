package ir.ebcom.websocket.ws.service;

import ir.ebcom.websocket.ws.model.UserSession;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addSession(UserSession userSession);

    void removeSession(String userId);

    Optional<UserSession> getSession(String userId);

    List<UserSession> getActiveUsers();
}

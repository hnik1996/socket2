package ir.ebcom.websocket.ws.service.impl;

import ir.ebcom.websocket.ws.model.UserSession;
import ir.ebcom.websocket.ws.repository.RepositoryFactory;
import ir.ebcom.websocket.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ComponentScan("ir.ebcom.websocket.ws.repository")
@Service
public class UserServiceImpl implements UserService {
    //    private UserServiceImpl(){}
    @Autowired
    private RepositoryFactory repositoryFactory;

    public void addSession(UserSession userSession) {
        repositoryFactory.getUserRepository().save(userSession);
    }

    public void removeSession(String userId) {
        repositoryFactory.getUserRepository().deleteById(userId);
    }

    public Optional<UserSession> getSession(String userId) {
        return repositoryFactory.getUserRepository().findById(userId);
    }

    public List<UserSession> getActiveUsers() {
        Iterable<UserSession> all = repositoryFactory.getUserRepository().findAll();
        return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
    }

    public static UserService getInstance() {
        return new UserServiceImpl();
    }
}

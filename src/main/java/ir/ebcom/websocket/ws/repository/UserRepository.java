package ir.ebcom.websocket.ws.repository;

import ir.ebcom.websocket.ws.model.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserSession, String> {
}

package ir.ebcom.websocket.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.websocket.Session;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("UserSession")
public class UserSession {
    @Id
    private String userId;
    private Session session;
}

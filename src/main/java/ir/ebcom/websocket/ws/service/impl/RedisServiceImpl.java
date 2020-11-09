package ir.ebcom.websocket.ws.service.impl;

import ir.ebcom.websocket.ws.controller.SocketEndpoint;
import ir.ebcom.websocket.ws.exception.BadRequestException;
import ir.ebcom.websocket.ws.model.UserSession;
import ir.ebcom.websocket.ws.repository.UserRepository;
import ir.ebcom.websocket.ws.service.RedisService;
import ir.ebcom.websocket.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Map;
import java.util.Optional;

@Service
public class RedisServiceImpl implements RedisService {
//    private final RedisRepository redisRepository;
    private final UserService userService;
    private final SocketEndpoint socketEndpoint;

//    @Autowired
//    public RedisServiceImpl(/*RedisRepository redisRepository, UserRepository userRepository,*/ SocketEndpoint socketEndpoint) {
//        this.redisRepository = redisRepository;
//        this.userRepository = userRepository;
//        this.socketEndpoint = socketEndpoint;
//    }


    @Autowired
    public RedisServiceImpl(UserService userService, SocketEndpoint socketEndpoint) {
        this.userService = userService;
        this.socketEndpoint = socketEndpoint;
    }

    @Override
    public void findByUserId(String userId, Map<String, Object> data) {
//        Optional<InputData> optionalData = redisRepository.findById(userId);
        Optional<UserSession> optionalUserSession = userService.getSession("userId");
        if (!optionalUserSession.isPresent()) throw new BadRequestException("user id not found");
        optionalUserSession.ifPresent(userSession ->  {
            socketEndpoint.onMessage("hiiiiii", userSession.getSession());
            socketEndpoint.onClose(userSession.getSession());
            userService.removeSession(userId);
        });
//        socketService.findOpenedByUserId(userId, data);


    }

//    @Override
//    public void deleteByUserId(String userId) {
//        redisRepository.deleteById(userId);
//    }
//
//    private void save() {
//        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("userId", UUID.randomUUID().toString());
//        stringObjectHashMap.put("clientId", UUID.randomUUID().toString());
//        String id = UUID.randomUUID().toString();
//        InputData inputData = new InputData(id, "save", stringObjectHashMap, new Date().getTime(), UUID.randomUUID().toString(), 0L);
//        InputData save = redisRepository.save(inputData);
//        Optional<InputData> byId = redisRepository.findById(id);
//        JsonNode jsonNode = new ObjectMapper().valueToTree(byId.get());
//        System.out.println(jsonNode);
//    }
}

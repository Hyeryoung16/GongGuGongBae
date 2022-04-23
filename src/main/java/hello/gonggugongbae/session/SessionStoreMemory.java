package hello.gonggugongbae.session;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SessionStoreMemory implements SessionStore{

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>(); // 동시 요청에 안전

    @Override
    public void put(String sessionId, Object value) {
        sessionStore.put(sessionId, value);
    }

    @Override
    public Object get(String sessionId) {
        return sessionStore.get(sessionId);
    }

    @Override
    public void remove(String sessionId) {
        sessionStore.remove(sessionId);
    }
}

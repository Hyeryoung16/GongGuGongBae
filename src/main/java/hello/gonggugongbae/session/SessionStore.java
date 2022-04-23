package hello.gonggugongbae.session;

public interface SessionStore {
    void put(String sessionId, Object value);
    Object get(String sessionId);
    void remove(String sessionId);
}

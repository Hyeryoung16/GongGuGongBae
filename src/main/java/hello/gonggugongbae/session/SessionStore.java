package hello.gonggugongbae.session;

import javax.servlet.http.Cookie;

public interface SessionStore {
    void put(String sessionId, Object value);
    Object get(String sessionId);
    void remove(String sessionId);
}

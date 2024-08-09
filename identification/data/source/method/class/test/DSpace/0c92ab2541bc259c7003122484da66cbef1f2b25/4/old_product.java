public Session getCurrentSession() {
        String sessionId = getCurrentSessionId();
        Session session = getSessionImpl(sessionId);
        return session;
    }
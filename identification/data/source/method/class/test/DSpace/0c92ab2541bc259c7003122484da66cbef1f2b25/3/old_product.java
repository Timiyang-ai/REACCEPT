public String getCurrentSessionId() {
        String sessionId = (String) getRequestCache().get(CachingService.SESSION_ID_KEY);
        return sessionId;
    }
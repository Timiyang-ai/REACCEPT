public String publishToken(final Server server, final SUser user) {
        UserToken userToken = UserToken.newToken(user.getId(), sessionIdGenerator.generateSessionId(), server.getId());
        UserTokenCache.getInstance().put(user.getId(), userToken);
        customKvStoreService.set(SESSION_STORE, userToken.getStoreKey(), userToken.toStoreValue(), sessionExpireSec, server);
        return userToken.toBearerToken();
    }
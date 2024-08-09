public String publishToken(final Server server, final SUser user) {
        UserToken userToken = UserToken.newToken(user.getId(), sessionIdGenerator.generateSessionId(), server.getId());
        UserTokenCache.getInstance().put(userToken);

        String mergedStoreValue = getAndMergeToStoredValue(userToken);
        customKvStoreService.set(SESSION_STORE, userToken.getUserId(), mergedStoreValue, sessionExpireSec, server);

        return userToken.toBearerToken();
    }
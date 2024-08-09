public void validateToken(UserToken token) {
        UserToken tokenTrusted = UserTokenCache.getInstance().get(token.getId());
        if (tokenTrusted == null) {
            String stored = customKvStoreService.get(SESSION_STORE, token.getStoreKey(), ServerManager.getInstance().getServerIfNullDefault(token.getServerId()));
            if (StringUtils.isNotBlank(stored)) {
                tokenTrusted = UserToken.fromStoreValue(stored, token.getServerId());
                if (tokenTrusted != null) {
                    UserTokenCache.getInstance().put(tokenTrusted.getId(), tokenTrusted);
                }
            }
        }
        if (tokenTrusted == null || !tokenTrusted.getToken().equals(token.getToken()) || tokenTrusted.isExpired(sessionExpireSec)) {
            throw ErrorState.SESSION_EXPIRED.newBizException();
        }
        if (tokenTrusted.needToBeRenewed(SessionTouchThresholdSec)) {
            touchToken(token);
        }
    }
public void validateToken(UserToken token) {
        UserToken tokenTrusted = UserTokenCache.getInstance().get(token);
        if (tokenTrusted == null) {
            tokenTrusted = getStoredMatchedToken(token);
            if (tokenTrusted != null) {
                UserTokenCache.getInstance().put(tokenTrusted);
            }
        }
        if (tokenTrusted == null || tokenTrusted.isExpired(sessionExpireSec)) {
            throw ErrorState.SESSION_EXPIRED.newBizException();
        }
        if (tokenTrusted.needToBeRenewed(SessionTouchThresholdSec)) {
            touchToken(token);
        }
    }
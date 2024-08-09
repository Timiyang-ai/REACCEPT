public static SyncUser logIn(final SyncCredentials credentials, final String authenticationUrl) throws ObjectServerError {
        URL authUrl = getUrl(authenticationUrl);

        ObjectServerError error;
        try {
            AuthenticateResponse result;
            if (credentials.getIdentityProvider().equals(SyncCredentials.IdentityProvider.ACCESS_TOKEN)) {
                // Credentials using ACCESS_TOKEN as IdentityProvider are optimistically assumed to be valid already.
                // So log them in directly without contacting the authentication server. This is done by mirroring
                // the JSON response expected from the server.
                String userIdentifier = credentials.getUserIdentifier();
                String token = (String) credentials.getUserInfo().get("_token");
                boolean isAdmin = (Boolean) credentials.getUserInfo().get("_isAdmin");
                result = AuthenticateResponse.createValidResponseWithUser(userIdentifier, token, isAdmin);
            } else {
                final AuthenticationServer server = SyncManager.getAuthServer();
                result = server.loginUser(credentials, authUrl);
            }
            if (result.isValid()) {
                SyncUser user = new SyncUser(result.getRefreshToken(), authUrl);
                RealmLog.info("Succeeded authenticating user.\n%s", user);
                SyncManager.getUserStore().put(user);
                SyncManager.notifyUserLoggedIn(user);
                return user;
            } else {
                RealmLog.info("Failed authenticating user.\n%s", result.getError());
                error = result.getError();
            }
        } catch (Throwable e) {
            throw new ObjectServerError(ErrorCode.UNKNOWN, e);
        }
        throw error;
    }
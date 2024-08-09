@SuppressWarnings("FutureReturnValueIgnored")
    public void logout() {
        // Acquire lock to prevent users creating new instances
        synchronized (Realm.class) {
            if (!SyncManager.getUserStore().isActive(identity, authenticationUrl.toString())) {
                return; // Already logged out status
            }

            // Mark the user as logged out in the ObjectStore
            SyncManager.getUserStore().remove(identity, authenticationUrl.toString());

            // invalidate all pending refresh_token queries
            for (SyncConfiguration syncConfiguration : realms.keySet()) {
                try {
                    SyncSession session = SyncManager.getSession(syncConfiguration);
                    session.clearScheduledAccessTokenRefresh();
                } catch (IllegalStateException e) {
                    if (!e.getMessage().contains("No SyncSession found")) {
                        throw e;
                    }// else no session, either the Realm was not opened or session was removed.
                }
            }

            // Remove all local tokens, preventing further connections.
            // don't remove identity as this SyncUser might be re-activated and we need
            // to avoid throwing a mismatch SyncConfiguration in RealmCache if we have
            // the similar SyncConfiguration using the same identity, but with different (new)
            // refresh-token.
            realms.clear();

            // Finally revoke server token. The local user is logged out in any case.
            final AuthenticationServer server = SyncManager.getAuthServer();
            // don't reference directly the refreshToken inside the revoke request
            // as it may revoke the newly acquired refresh_token
            final Token refreshTokenToBeRevoked = refreshToken;

            ThreadPoolExecutor networkPoolExecutor = SyncManager.NETWORK_POOL_EXECUTOR;
            networkPoolExecutor.submit(new ExponentialBackoffTask<LogoutResponse>(3) {

                @Override
                protected LogoutResponse execute() {
                    return server.logout(refreshTokenToBeRevoked, getAuthenticationUrl());
                }

                @Override
                protected void onSuccess(LogoutResponse response) {
                    SyncManager.notifyUserLoggedOut(SyncUser.this);
                }

                @Override
                protected void onError(LogoutResponse response) {
                    RealmLog.error("Failed to log user out.\n" + response.getError().toString());
                }
            });
        }
    }
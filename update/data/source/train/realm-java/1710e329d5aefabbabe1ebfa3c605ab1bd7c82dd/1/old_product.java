public void logout() {
        // Acquire lock to prevent users creating new instances
        synchronized (Realm.class) {
            if (!SyncManager.getUserStore().isActive(identity)) {
                return; // Already logged out status
            }

//            // The ObjectStore will delete the associated Realms after the next app launch
//            // we can also have an optimistic approach and try to delete these Realms unless there's
//            // a remaining instance open.
//            for (final SyncConfiguration syncConfiguration : realms.keySet()) {
//                RealmCache.invokeWithGlobalRefCount(syncConfiguration, new RealmCache.Callback() {
//                    @Override
//                    public void onResult(int count) {
//                        if (count == 0) {
//                            // all instances are closed, remove the Realm
//                            File realmFile = new File(syncConfiguration.getPath());
//                            if (realmFile.exists() && !Util.deleteRealm(syncConfiguration.getPath(), realmFile.getParentFile(), realmFile.getName())) {
//                                RealmLog.error("Could not delete Realm when user logged out: " + syncConfiguration.getPath());
//                            }
//                        }
//                    }
//                });
//            }

            // Mark the user as logged out in the ObjectStore
            SyncManager.getUserStore().remove(identity);

            // Remove all local tokens, preventing further connections.
            realms.clear();

            // Finally revoke server token. The local user is logged out in any case.
            final AuthenticationServer server = SyncManager.getAuthServer();
            ThreadPoolExecutor networkPoolExecutor = SyncManager.NETWORK_POOL_EXECUTOR;

            Future<?> submit = networkPoolExecutor.submit(new ExponentialBackoffTask<LogoutResponse>() {

                @Override
                protected LogoutResponse execute() {
                    return server.logout(refreshToken, getAuthenticationUrl());
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
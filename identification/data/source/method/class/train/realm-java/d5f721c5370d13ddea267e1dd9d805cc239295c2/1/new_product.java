public static RealmAsyncTask loginAsync(final SyncCredentials credentials, final String authenticationUrl, final Callback callback) {
        checkLooperThread("Asynchronous login is only possible from looper threads.");
        return new Request(SyncManager.NETWORK_POOL_EXECUTOR, callback) {
            @Override
            public SyncUser run() throws ObjectServerError {
                return login(credentials, authenticationUrl);
            }
        }.start();
    }
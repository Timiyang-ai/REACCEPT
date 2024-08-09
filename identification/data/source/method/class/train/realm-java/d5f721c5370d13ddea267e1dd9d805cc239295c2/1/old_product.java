public static RealmAsyncTask loginAsync(final SyncCredentials credentials, final String authenticationUrl, final Callback callback) {
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("Asynchronous login is only possible from looper threads.");
        }
        final Handler handler = new Handler(Looper.myLooper());
        ThreadPoolExecutor networkPoolExecutor = SyncManager.NETWORK_POOL_EXECUTOR;
        Future<?> authenticateRequest = networkPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SyncUser user = login(credentials, authenticationUrl);
                    postSuccess(user);
                } catch (ObjectServerError e) {
                    postError(e);
                }
            }

            private void postError(final ObjectServerError error) {
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callback.onError(error);
                            } catch (Exception e) {
                                RealmLog.info("onError has thrown an exception but is ignoring it: %s",
                                        Util.getStackTrace(e));
                            }
                        }
                    });
                }
            }

            private void postSuccess(final SyncUser user) {
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(user);
                        }
                    });
                }
            }
        });

        return new RealmAsyncTaskImpl(authenticateRequest, networkPoolExecutor);
    }
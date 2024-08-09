@Test
    public void loginAsync_errorHandlerThrows() throws InterruptedException {
        final AtomicBoolean errorThrown = new AtomicBoolean(false);

        // Create custom Looper thread to be able to check for errors thrown when processing Looper events.
        Thread t = new Thread(new Runnable() {
            private volatile Handler handler;
            @Override
            public void run() {
                Looper.prepare();
                try {
                    handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            SyncCredentials credentials = SyncCredentials.usernamePassword("IWantToHackYou", "GeneralPassword", false);
                            SyncUser.loginAsync(credentials, Constants.AUTH_URL, new SyncUser.Callback() {
                                @Override
                                public void onSuccess(SyncUser user) {
                                    fail();
                                }

                                @Override
                                public void onError(ObjectServerError error) {
                                    assertEquals(ErrorCode.INVALID_CREDENTIALS, error.getErrorCode());
                                    throw new IllegalArgumentException("BOOM");
                                }
                            });
                        }
                    });
                    Looper.loop(); //
                } catch (IllegalArgumentException e) {
                    errorThrown.set(true);
                }
            }
        });
        t.start();
        t.join(TimeUnit.SECONDS.toMillis(10));
        assertTrue(errorThrown.get());
    }
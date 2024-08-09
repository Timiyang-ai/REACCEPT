@Test
    @RunTestInLooperThread
    public void loginAsync_errorHandlerThrows() {
        // set log level to info to make sure the IllegalArgumentException
        // thrown in the test is visible in Logcat
        final int defaultLevel = RealmLog.getLevel();
        RealmLog.setLevel(LogLevel.INFO);
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

        looperThread.postRunnableDelayed(new Runnable() {
            @Override
            public void run() {
                RealmLog.setLevel(defaultLevel);
                looperThread.testComplete();
            }
        }, 1000);
    }
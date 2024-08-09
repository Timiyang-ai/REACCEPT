@Test
    public void testSendCustomCommand_onConnect() throws InterruptedException {
        prepareLooper();
        final CountDownLatch latch = new CountDownLatch(1);
        final SessionCommand testCommand = new SessionCommand("test", null);
        final SessionCallback testSessionCallback = new SessionCallback() {
            @Nullable
            @Override
            public SessionCommandGroup onConnect(@NonNull MediaSession session,
                    @NonNull ControllerInfo controller) {
                session.sendCustomCommand(controller, testCommand, null);
                return super.onConnect(session, controller);
            }
        };
        final ControllerCallback testControllerCallback = new ControllerCallback() {
            @NonNull
            @Override
            public SessionResult onCustomCommand(@NonNull MediaController controller,
                    @NonNull SessionCommand command, @Nullable Bundle args) {
                if (TextUtils.equals(testCommand.getCustomAction(), command.getCustomAction())) {
                    latch.countDown();
                }
                return super.onCustomCommand(controller, command, args);
            }
        };
        try (MediaSession session = new MediaSession.Builder(mContext, mPlayer)
                .setSessionCallback(sHandlerExecutor, testSessionCallback).build()) {
            MediaController controller = createController(session.getToken(), true,
                    null, testControllerCallback);
            assertFalse(latch.await(TIMEOUT_MS, TimeUnit.MILLISECONDS));
        }
    }
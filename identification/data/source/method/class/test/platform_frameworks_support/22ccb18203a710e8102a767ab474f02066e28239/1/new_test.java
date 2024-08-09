@Ignore // TODO: remove @Ignore
    @Test
    public void testGetRepeatMode() throws InterruptedException {
        final int testRepeatMode = MediaPlaylistAgent.REPEAT_MODE_GROUP;
        mMockAgent.setRepeatMode(testRepeatMode);

        final CountDownLatch latch = new CountDownLatch(1);
        final SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public void onRepeatModeChanged(MediaSession2 session, MediaPlaylistAgent playlistAgent,
                    int repeatMode) {
                assertEquals(mMockAgent, playlistAgent);
                assertEquals(testRepeatMode, repeatMode);
                latch.countDown();
            }
        };
        try (MediaSession2 session = new MediaSession2.Builder(mContext)
                .setPlayer(mPlayer)
                .setPlaylistAgent(mMockAgent)
                .setId("testGetRepeatMode")
                .setSessionCallback(sHandlerExecutor, sessionCallback)
                .build()) {
            mMockAgent.notifyRepeatModeChanged();
            assertTrue(latch.await(TIMEOUT_MS, TimeUnit.MILLISECONDS));
        }
    }
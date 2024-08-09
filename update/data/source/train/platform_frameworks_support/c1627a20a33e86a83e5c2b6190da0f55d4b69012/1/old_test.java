@Ignore
    @Test
    public void testGetRepeatMode() throws InterruptedException {
        final int testRepeatMode = MediaPlaylistAgent.REPEAT_MODE_GROUP;
        final MediaPlaylistAgent agent = new MediaPlaylistAgent() {
            @Override
            public int getRepeatMode() {
                return testRepeatMode;
            }
        };
        final CountDownLatch latch = new CountDownLatch(1);
        final SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public void onRepeatModeChanged(MediaSession2 session, MediaPlaylistAgent playlistAgent,
                    int repeatMode) {
                assertEquals(agent, playlistAgent);
                assertEquals(testRepeatMode, repeatMode);
                latch.countDown();
            }
        };
        try (MediaSession2 session = new MediaSession2.Builder(mContext)
                .setPlayer(mPlayer)
                .setPlaylistAgent(agent)
                .setId("testGetRepeatMode")
                .setSessionCallback(sHandlerExecutor, sessionCallback)
                .build()) {
            agent.notifyRepeatModeChanged();
            assertTrue(latch.await(TIMEOUT_MS, TimeUnit.MILLISECONDS));
        }
    }
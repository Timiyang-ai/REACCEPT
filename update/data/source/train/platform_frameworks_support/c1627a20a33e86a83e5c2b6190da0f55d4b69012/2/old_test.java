@Ignore
    @Test
    public void testGetShuffleMode() throws InterruptedException {
        final int testShuffleMode = MediaPlaylistAgent.SHUFFLE_MODE_GROUP;
        final MediaPlaylistAgent agent = new MediaPlaylistAgent() {
            @Override
            public int getShuffleMode() {
                return testShuffleMode;
            }
        };
        final CountDownLatch latch = new CountDownLatch(1);
        final SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public void onShuffleModeChanged(MediaSession2 session,
                    MediaPlaylistAgent playlistAgent, int shuffleMode) {
                assertEquals(agent, playlistAgent);
                assertEquals(testShuffleMode, shuffleMode);
                latch.countDown();
            }
        };
        try (MediaSession2 session = new MediaSession2.Builder(mContext)
                .setPlayer(mPlayer)
                .setPlaylistAgent(agent)
                .setId("testGetShuffleMode")
                .setSessionCallback(sHandlerExecutor, sessionCallback)
                .build()) {
            agent.notifyShuffleModeChanged();
            assertTrue(latch.await(TIMEOUT_MS, TimeUnit.MILLISECONDS));
        }
    }
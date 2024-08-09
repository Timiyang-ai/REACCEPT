@Ignore
    @Test
    public void testGetShuffleMode() throws InterruptedException {
        final int testShuffleMode = MediaPlaylistAgent.SHUFFLE_MODE_GROUP;
        mMockAgent.setShuffleMode(testShuffleMode);

        final CountDownLatch latch = new CountDownLatch(1);
        final SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public void onShuffleModeChanged(MediaSession2 session,
                    MediaPlaylistAgent playlistAgent, int shuffleMode) {
                assertEquals(mMockAgent, playlistAgent);
                assertEquals(testShuffleMode, shuffleMode);
                latch.countDown();
            }
        };
        try (MediaSession2 session = new MediaSession2.Builder(mContext)
                .setPlayer(mPlayer)
                .setPlaylistAgent(mMockAgent)
                .setId("testGetShuffleMode")
                .setSessionCallback(sHandlerExecutor, sessionCallback)
                .build()) {
            mMockAgent.notifyShuffleModeChanged();
            assertTrue(latch.await(TIMEOUT_MS, TimeUnit.MILLISECONDS));
        }
    }
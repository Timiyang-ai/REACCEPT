    private void prepareTestSkeleton(final PlayerStatus initialState, long timeoutSeconds) throws InterruptedException {
        final Context c = getInstrumentation().getTargetContext();
        final int latchCount = 1;
        final CountDownLatch countDownLatch = new CountDownLatch(latchCount);
        CancelablePSMPCallback callback = new CancelablePSMPCallback(new DefaultPSMPCallback() {
            @Override
            public void statusChanged(LocalPSMP.PSMPInfo newInfo) {
                checkPSMPInfo(newInfo);
                if (newInfo.playerStatus == PlayerStatus.ERROR) {
                    if (assertionError == null)
                        assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                } else {
                    if (initialState == PlayerStatus.INITIALIZED && newInfo.playerStatus == PlayerStatus.PREPARED) {
                        countDownLatch.countDown();
                    } else if (initialState != PlayerStatus.INITIALIZED && initialState == newInfo.playerStatus) {
                        countDownLatch.countDown();
                    }
                }
            }

            @Override
            public boolean onMediaPlayerError(Object inObj, int what, int extra) {
                if (assertionError == null)
                    assertionError = new AssertionFailedError("Unexpected call to onMediaPlayerError");
                return false;
            }
        });
        PlaybackServiceMediaPlayer psmp = new LocalPSMP(c, callback);
        Playable p = writeTestPlayable(playableFileUrl, PLAYABLE_LOCAL_URL);
        if (initialState == PlayerStatus.INITIALIZED
                || initialState == PlayerStatus.PLAYING
                || initialState == PlayerStatus.PREPARED
                || initialState == PlayerStatus.PAUSED) {
            boolean prepareImmediately = (initialState != PlayerStatus.INITIALIZED);
            boolean startWhenPrepared = (initialState != PlayerStatus.PREPARED);
            psmp.playMediaObject(p, false, startWhenPrepared, prepareImmediately);
            if (initialState == PlayerStatus.PAUSED) {
                psmp.pause(false, false);
            }
            psmp.prepare();
        }

        boolean res = countDownLatch.await(timeoutSeconds, TimeUnit.SECONDS);
        if (initialState != PlayerStatus.INITIALIZED) {
            assertEquals(initialState, psmp.getPSMPInfo().playerStatus);
        }

        if (assertionError != null)
            throw assertionError;
        assertTrue(res);
        callback.cancel();
        psmp.shutdown();
    }
    private void reinitTestSkeleton(final PlayerStatus initialState, final long timeoutSeconds) throws InterruptedException {
        final Context c = getInstrumentation().getTargetContext();
        final int latchCount = 2;
        final CountDownLatch countDownLatch = new CountDownLatch(latchCount);
        CancelablePSMPCallback callback = new CancelablePSMPCallback(new DefaultPSMPCallback() {
            @Override
            public void statusChanged(LocalPSMP.PSMPInfo newInfo) {
                checkPSMPInfo(newInfo);
                if (newInfo.playerStatus == PlayerStatus.ERROR) {
                    if (assertionError == null)
                        assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                } else {
                    if (newInfo.playerStatus == initialState) {
                        countDownLatch.countDown();
                    } else if (countDownLatch.getCount() < latchCount && newInfo.playerStatus == PlayerStatus.INITIALIZED) {
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
        boolean prepareImmediately = initialState != PlayerStatus.INITIALIZED;
        boolean startImmediately = initialState != PlayerStatus.PREPARED;
        psmp.playMediaObject(p, false, startImmediately, prepareImmediately);
        if (initialState == PlayerStatus.PAUSED) {
            psmp.pause(false, false);
        }
        psmp.reinit();
        boolean res = countDownLatch.await(timeoutSeconds, TimeUnit.SECONDS);
        if (assertionError != null)
            throw assertionError;
        assertTrue(res);
        callback.cancel();
        psmp.shutdown();
    }
    private void pauseTestSkeleton(final PlayerStatus initialState, final boolean stream, final boolean abandonAudioFocus, final boolean reinit, long timeoutSeconds) throws InterruptedException {
        final Context c = getInstrumentation().getTargetContext();
        final int latchCount = (stream && reinit) ? 2 : 1;
        final CountDownLatch countDownLatch = new CountDownLatch(latchCount);

        CancelablePSMPCallback callback = new CancelablePSMPCallback(new DefaultPSMPCallback() {
            @Override
            public void statusChanged(LocalPSMP.PSMPInfo newInfo) {
                checkPSMPInfo(newInfo);
                if (newInfo.playerStatus == PlayerStatus.ERROR) {
                    if (assertionError == null)
                        assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                } else if (initialState != PlayerStatus.PLAYING) {
                    if (assertionError == null)
                        assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                } else {
                    switch (newInfo.playerStatus) {
                        case PAUSED:
                            if (latchCount == countDownLatch.getCount())
                                countDownLatch.countDown();
                            else {
                                if (assertionError == null)
                                    assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                            }
                            break;
                        case INITIALIZED:
                            if (stream && reinit && countDownLatch.getCount() < latchCount) {
                                countDownLatch.countDown();
                            } else if (countDownLatch.getCount() < latchCount) {
                                if (assertionError == null)
                                    assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                            }
                            break;
                    }
                }

            }

            @Override
            public void shouldStop() {
                if (assertionError == null)
                    assertionError = new AssertionFailedError("Unexpected call to shouldStop");
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
        if (initialState == PlayerStatus.PLAYING) {
            psmp.playMediaObject(p, stream, true, true);
        }
        psmp.pause(abandonAudioFocus, reinit);
        boolean res = countDownLatch.await(timeoutSeconds, TimeUnit.SECONDS);
        if (assertionError != null)
            throw assertionError;
        assertTrue(res || initialState != PlayerStatus.PLAYING);
        callback.cancel();
        psmp.shutdown();
    }
    private void resumeTestSkeleton(final PlayerStatus initialState, long timeoutSeconds) throws InterruptedException {
        final Context c = getInstrumentation().getTargetContext();
        final int latchCount = (initialState == PlayerStatus.PAUSED || initialState == PlayerStatus.PLAYING) ? 2 :
                (initialState == PlayerStatus.PREPARED) ? 1 : 0;
        final CountDownLatch countDownLatch = new CountDownLatch(latchCount);

        CancelablePSMPCallback callback = new CancelablePSMPCallback(new DefaultPSMPCallback() {
            @Override
            public void statusChanged(LocalPSMP.PSMPInfo newInfo) {
                checkPSMPInfo(newInfo);
                if (newInfo.playerStatus == PlayerStatus.ERROR) {
                    if (assertionError == null)
                        assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                } else if (newInfo.playerStatus == PlayerStatus.PLAYING) {
                    if (countDownLatch.getCount() == 0) {
                        if (assertionError == null)
                            assertionError = new UnexpectedStateChange(newInfo.playerStatus);
                    } else {
                        countDownLatch.countDown();
                    }
                }

            }

            @Override
            public boolean onMediaPlayerError(Object inObj, int what, int extra) {
                if (assertionError == null) {
                    assertionError = new AssertionFailedError("Unexpected call of onMediaPlayerError");
                }
                return false;
            }
        });
        PlaybackServiceMediaPlayer psmp = new LocalPSMP(c, callback);
        if (initialState == PlayerStatus.PREPARED || initialState == PlayerStatus.PLAYING || initialState == PlayerStatus.PAUSED) {
            boolean startWhenPrepared = (initialState != PlayerStatus.PREPARED);
            psmp.playMediaObject(writeTestPlayable(playableFileUrl, PLAYABLE_LOCAL_URL), false, startWhenPrepared, true);
        }
        if (initialState == PlayerStatus.PAUSED) {
            psmp.pause(false, false);
        }
        psmp.resume();
        boolean res = countDownLatch.await(timeoutSeconds, TimeUnit.SECONDS);
        if (assertionError != null)
            throw assertionError;
        assertTrue(res || (initialState != PlayerStatus.PAUSED && initialState != PlayerStatus.PREPARED));
        callback.cancel();
        psmp.shutdown();
    }
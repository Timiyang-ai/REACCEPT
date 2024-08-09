public void pause(final boolean abandonFocus, final boolean reinit) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                playerLock.lock();

                if (playerStatus == PlayerStatus.PLAYING) {
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "Pausing playback.");
                    mediaPlayer.pause();
                    setPlayerStatus(PlayerStatus.PAUSED, media);

                    if (abandonFocus) {
                        audioManager.abandonAudioFocus(audioFocusChangeListener);
                        pausedBecauseOfTransientAudiofocusLoss = false;
                    }
                    if (stream && reinit) {
                        reinit();
                    }
                } else {
                    if (BuildConfig.DEBUG) Log.d(TAG, "Ignoring call to pause: Player is in " + playerStatus + " state");
                }

                playerLock.unlock();
            }
        });
    }
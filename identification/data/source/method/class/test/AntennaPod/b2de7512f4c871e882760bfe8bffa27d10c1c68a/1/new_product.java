public void pause(final boolean abandonFocus, final boolean reinit) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                playerLock.lock();
                releaseWifiLockIfNecessary();
                if (playerStatus == PlayerStatus.PLAYING) {
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
                    Log.d(TAG, "Ignoring call to pause: Player is in " + playerStatus + " state");
                }

                playerLock.unlock();
            }
        });
    }
public void resume() {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                playerLock.lock();
                if (playerStatus == PlayerStatus.PAUSED || playerStatus == PlayerStatus.PREPARED) {
                    int focusGained = audioManager.requestAudioFocus(
                            audioFocusChangeListener, AudioManager.STREAM_MUSIC,
                            AudioManager.AUDIOFOCUS_GAIN);
                    if (focusGained == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                        setSpeed(Float.parseFloat(UserPreferences.getPlaybackSpeed()));
                        mediaPlayer.start();
                        if (playerStatus == PlayerStatus.PREPARED) {
                            mediaPlayer.seekTo(media.getPosition());
                        }
                        setPlayerStatus(PlayerStatus.PLAYING, media);
                        pausedBecauseOfTransientAudiofocusLoss = false;
                        if (android.os.Build.VERSION.SDK_INT >= 14) {
                            RemoteControlClient remoteControlClient = callback.getRemoteControlClient();
                            if (remoteControlClient != null) {
                                audioManager
                                        .registerRemoteControlClient(remoteControlClient);
                            }
                        }
                        audioManager
                                .registerMediaButtonEventReceiver(new ComponentName(context.getPackageName(),
                                        MediaButtonReceiver.class.getName()));
                        media.onPlaybackStart();

                    } else {
                        if (AppConfig.DEBUG) Log.e(TAG, "Failed to request audio focus");
                    }
                } else {
                    if (AppConfig.DEBUG)
                        Log.d(TAG, "Call to resume() was ignored because current state of PSMP object is " + playerStatus);
                }

                playerLock.unlock();
            }
        });
    }
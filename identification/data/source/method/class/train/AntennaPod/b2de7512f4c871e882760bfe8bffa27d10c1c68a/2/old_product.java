public void reinit() {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                playerLock.lock();
                releaseWifiLockIfNecessary();
                if (media != null) {
                    playMediaObject(media, true, stream, startWhenPrepared.get(), false);
                } else if (mediaPlayer != null) {
                    mediaPlayer.reset();
                } else {
                    Log.d(TAG, "Call to reinit was ignored: media and mediaPlayer were null");
                }
                playerLock.unlock();
            }
        });
    }
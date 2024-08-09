public void prepare() {
        executor.submit(() -> {
            playerLock.lock();

            if (playerStatus == PlayerStatus.INITIALIZED) {
                Log.d(TAG, "Preparing media player");
                setPlayerStatus(PlayerStatus.PREPARING, media);
                try {
                    mediaPlayer.prepare();
                    onPrepared(startWhenPrepared.get());
                } catch (IOException e) {
                    e.printStackTrace();
                    setPlayerStatus(PlayerStatus.ERROR, null);
                }
            }
            playerLock.unlock();

        });
    }
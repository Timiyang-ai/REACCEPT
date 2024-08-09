public void resume() {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                playerLock.lock();
                resumeSync();
                playerLock.unlock();
            }
        });
    }
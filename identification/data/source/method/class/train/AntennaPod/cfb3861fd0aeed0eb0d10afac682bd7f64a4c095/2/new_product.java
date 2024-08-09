public void resume() {
        executor.submit(() -> {
            playerLock.lock();
            resumeSync();
            playerLock.unlock();
        });
    }
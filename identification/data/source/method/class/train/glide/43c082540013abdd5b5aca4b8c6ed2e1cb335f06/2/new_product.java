public void pauseRequests() {
        isPaused = true;
        for (Request request : requests) {
            if (request.isRunning()) {
                request.pause();
            }
        }
    }
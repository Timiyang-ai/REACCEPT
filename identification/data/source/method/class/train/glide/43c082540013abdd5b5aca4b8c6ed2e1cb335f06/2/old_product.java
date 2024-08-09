public void pauseRequests() {
        isPaused = true;
        for (Request request : requests) {
            if (!request.isComplete() && !request.isFailed()) {
                request.clear();
            }
        }
    }
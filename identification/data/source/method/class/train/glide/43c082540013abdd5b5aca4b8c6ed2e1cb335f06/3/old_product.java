public void pauseRequests() {
        for (Request request : requests) {
            if (!request.isComplete() && !request.isFailed()) {
                request.clear();
            }
        }
    }
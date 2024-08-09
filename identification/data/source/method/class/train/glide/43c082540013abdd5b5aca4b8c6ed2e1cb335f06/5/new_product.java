public void pauseRequests() {
    isPaused = true;
    for (Request request : getSnapshot()) {
      if (request.isRunning()) {
        request.pause();
        pendingRequests.add(request);
      }
    }
  }
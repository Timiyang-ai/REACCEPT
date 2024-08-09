public void pauseRequests() {
    isPaused = true;
    for (Request request : Util.getSnapshot(requests)) {
      if (request.isRunning()) {
        request.clear();
        pendingRequests.add(request);
      }
    }
  }
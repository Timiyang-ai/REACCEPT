public void runRequest(Request request) {
    requests.add(request);
    if (!isPaused) {
      request.begin();
    } else {
      pendingRequests.add(request);
    }
  }
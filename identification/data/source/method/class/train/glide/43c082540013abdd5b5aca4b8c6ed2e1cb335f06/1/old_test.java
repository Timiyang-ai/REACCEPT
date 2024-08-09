  @Test
  public void pauseRequests_pausesRunningRequest() {
    FakeRequest request = new FakeRequest();
    request.setIsRunning();
    tracker.addRequest(request);
    tracker.pauseRequests();

    assertThat(request.isCleared()).isTrue();
  }
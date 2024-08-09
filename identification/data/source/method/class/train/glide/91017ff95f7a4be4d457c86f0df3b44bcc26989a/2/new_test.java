  @Test
  public void runRequest_startsRequest() {
    FakeRequest request = new FakeRequest();
    tracker.runRequest(request);

    assertThat(request.isRunning()).isTrue();
  }
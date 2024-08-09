  @Test
  public void setWorker() {
    // We should be able to set the worker the first time.
    statusClient.setWorker(worker, executionContext);

    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("setWorker once");
    statusClient.setWorker(worker, executionContext);
  }
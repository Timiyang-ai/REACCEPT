  @Test
  public void shutdownLbRpc_verifyChannelShutdown() throws Exception {
    xdsComms.shutdown();
    assertTrue(streamRecorder.awaitCompletion(1, TimeUnit.SECONDS));
    assertEquals(Status.Code.CANCELLED, Status.fromThrowable(streamRecorder.getError()).getCode());
    assertTrue(channel.isShutdown());
  }
  @Test
  public void getChannel() throws ExecutionException, InterruptedException {
    TestChannel root = new TestChannel();
    assertChannelNotFound(root.getLogId().getId());

    channelz.addRootChannel(root);
    assertEquals(
        GetChannelResponse
            .newBuilder()
            .setChannel(ChannelzProtoUtil.toChannel(root))
            .build(),
        getChannelHelper(root.getLogId().getId()));

    channelz.removeRootChannel(root);
    assertChannelNotFound(root.getLogId().getId());
  }
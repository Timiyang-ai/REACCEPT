  @Test
  public void getSubchannel() throws Exception {
    TestChannel subchannel = new TestChannel();
    assertSubchannelNotFound(subchannel.getLogId().getId());

    channelz.addSubchannel(subchannel);
    assertEquals(
        GetSubchannelResponse
            .newBuilder()
            .setSubchannel(ChannelzProtoUtil.toSubchannel(subchannel))
            .build(),
        getSubchannelHelper(subchannel.getLogId().getId()));

    channelz.removeSubchannel(subchannel);
    assertSubchannelNotFound(subchannel.getLogId().getId());
  }
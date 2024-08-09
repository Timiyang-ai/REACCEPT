  @Test
  public void getRootChannels_empty() {
    RootChannelList rootChannels = channelz.getRootChannels(/*fromId=*/ 0, /*maxPageSize=*/ 1);
    assertTrue(rootChannels.end);
    assertThat(rootChannels.channels).isEmpty();
  }
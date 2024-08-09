  @Test
  public void getTopChannels_empty() {
    assertEquals(
        GetTopChannelsResponse.newBuilder().setEnd(true).build(),
        getTopChannelHelper(0));
  }
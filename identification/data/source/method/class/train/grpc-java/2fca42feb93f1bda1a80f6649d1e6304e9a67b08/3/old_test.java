  @Test
  public void getSubchannel() {
    InternalInstrumented<ChannelStats> sub = create();
    assertNull(channelz.getSubchannel(id(sub)));

    channelz.addSubchannel(sub);
    assertSame(sub, channelz.getSubchannel(id(sub)));
    assertNull(channelz.getChannel(id(sub)));

    channelz.removeSubchannel(sub);
    assertNull(channelz.getSubchannel(id(sub)));
  }
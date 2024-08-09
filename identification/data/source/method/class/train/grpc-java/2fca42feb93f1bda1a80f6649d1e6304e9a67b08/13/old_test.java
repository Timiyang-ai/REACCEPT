  @Test
  public void getChannel() {
    InternalInstrumented<ChannelStats> root = create();
    assertNull(channelz.getChannel(id(root)));

    channelz.addRootChannel(root);
    assertSame(root, channelz.getChannel(id(root)));
    assertNull(channelz.getSubchannel(id(root)));

    channelz.removeRootChannel(root);
    assertNull(channelz.getRootChannel(id(root)));
  }
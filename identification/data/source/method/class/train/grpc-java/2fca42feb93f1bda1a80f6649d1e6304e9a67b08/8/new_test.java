  @Test
  public void getSocket() {
    InternalInstrumented<SocketStats> socket = create();
    assertNull(channelz.getSocket(id(socket)));

    channelz.addClientSocket(socket);
    assertSame(socket, channelz.getSocket(id(socket)));

    channelz.removeClientSocket(socket);
    assertNull(channelz.getSocket(id(socket)));
  }
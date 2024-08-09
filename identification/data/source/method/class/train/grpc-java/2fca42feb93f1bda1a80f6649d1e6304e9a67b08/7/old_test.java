  @Test
  public void getSocket() throws Exception {
    TestSocket socket = new TestSocket();
    assertSocketNotFound(socket.getLogId().getId());

    channelz.addClientSocket(socket);
    assertEquals(
        GetSocketResponse
            .newBuilder()
            .setSocket(ChannelzProtoUtil.toSocket(socket))
            .build(),
        getSocketHelper(socket.getLogId().getId()));

    channelz.removeClientSocket(socket);
    assertSocketNotFound(socket.getLogId().getId());
  }
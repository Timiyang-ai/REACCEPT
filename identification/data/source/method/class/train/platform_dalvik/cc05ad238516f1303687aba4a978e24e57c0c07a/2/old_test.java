@TestInfo(
              level = TestLevel.PARTIAL,
              purpose = "",
              targets = {
                @TestTarget(
                  methodName = "connect",
                  methodArgs = {java.net.SocketAddress.class}
                )
            })
    public void testConnect_NonBlockWithServer() throws IOException {
        // Non blocking mode
        this.channel1.configureBlocking(false);
        connectLocalServer();
        datagramSocket1.close();
        disconnectAfterConnected();
    }
@Test
  public void testEstablishSSHTunnelIfNeeded() throws Exception {
    // Mock host to verified
    String mockHost = "host0";
    int mockPort = 9049;
    String mockEndpoint = String.format("%s:%d", mockHost, mockPort);
    InetSocketAddress mockAddr = NetworkUtils.getInetSocketAddress(mockEndpoint);

    int mockFreePort = 9519;

    String tunnelHost = "tunnelHost";
    int timeout = -1;
    int retryCount = -1;
    int retryIntervalMs = -1;
    int verifyCount = -1;
    boolean isVerbose = true;

    // Can reach directly, no need to ssh tunnel
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(true).when(NetworkUtils.class, "isLocationReachable",
        Mockito.eq(mockAddr), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());

    Pair<InetSocketAddress, Process> ret =
        NetworkUtils.establishSSHTunnelIfNeeded(NetworkUtils.getInetSocketAddress(mockEndpoint),
            tunnelHost, timeout, retryCount, retryIntervalMs, verifyCount, isVerbose);
    Assert.assertEquals(mockHost, ret.first.getHostName());
    Assert.assertEquals(mockPort, ret.first.getPort());
    Assert.assertEquals(mockEndpoint, ret.first.toString());
    Assert.assertNull(ret.second);

    // Can not reach directly, basic setup
    PowerMockito.doReturn(false).when(NetworkUtils.class, "isLocationReachable",
        Mockito.eq(mockAddr), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
    PowerMockito.spy(SysUtils.class);
    PowerMockito.doReturn(mockFreePort).when(SysUtils.class, "getFreePort");
    Process process = Mockito.mock(Process.class);
    Mockito.doReturn(true).when(process).isAlive();

    // Can not reach directly, failed to establish ssh tunnel either
    PowerMockito.spy(ShellUtils.class);
    PowerMockito.doReturn(process).when(ShellUtils.class, "establishSSHTunnelProcess",
        Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(),
        Mockito.anyInt(), Mockito.anyBoolean());

    InetSocketAddress newAddress =
        NetworkUtils.getInetSocketAddress(
            String.format("%s:%d", NetworkUtils.LOCAL_HOST, mockFreePort));

    PowerMockito.doReturn(false).when(NetworkUtils.class, "isLocationReachable",
        Mockito.eq(newAddress), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
    ret =
        NetworkUtils.establishSSHTunnelIfNeeded(NetworkUtils.getInetSocketAddress(mockEndpoint),
            tunnelHost, timeout, retryCount, retryIntervalMs, verifyCount, isVerbose);
    Assert.assertNull(ret.first);
    Assert.assertNull(ret.second);

    // Can not reach directly, but can establish ssh tunnel to reach the destination
    PowerMockito.doReturn(true).when(NetworkUtils.class, "isLocationReachable",
        Mockito.eq(newAddress), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
    ret =
        NetworkUtils.establishSSHTunnelIfNeeded(NetworkUtils.getInetSocketAddress(mockEndpoint),
            tunnelHost, timeout, retryCount, retryIntervalMs, verifyCount, isVerbose);
    Assert.assertEquals(NetworkUtils.LOCAL_HOST, ret.first.getHostName());
    Assert.assertEquals(mockFreePort, ret.first.getPort());
    Assert.assertEquals(process, ret.second);
  }
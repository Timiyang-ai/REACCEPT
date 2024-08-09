@Test
  public void testSetupZkTunnel() throws Exception {
    String host0 = "host0";
    int port0 = 12;
    InetSocketAddress address0 =
        NetworkUtils.getInetSocketAddress(String.format("%s:%d", host0, port0));
    String host1 = "host1";
    int port1 = 13;
    InetSocketAddress address1 =
        NetworkUtils.getInetSocketAddress(String.format("%s:%d", host1, port1));
    String host2 = "host2";
    int port2 = 9049;
    InetSocketAddress address2 =
        NetworkUtils.getInetSocketAddress(String.format("%s:%d", host2, port2));

    String tunnelHost = "tunnelHost";
    int tunnelPort = 9519;
    InetSocketAddress tunnelAddress =
        NetworkUtils.getInetSocketAddress(String.format("%s:%d", tunnelHost, tunnelPort));

    // Original connection String
    String connectionString = String.format("%s:%d, %s:%d,  %s:%d   ",
        host0, port0, host1, port1, host2, port2);

    Config config = Mockito.mock(Config.class);
    Mockito.when(config.getStringValue(ConfigKeys.get("STATEMGR_CONNECTION_STRING"))).
        thenReturn(connectionString);
    NetworkUtils.TunnelConfig tunnelConfig =
        NetworkUtils.TunnelConfig.build(config, NetworkUtils.HeronSystem.STATE_MANAGER);

    Process process = Mockito.mock(Process.class);

    // Mock the invocation of establishSSHTunnelIfNeeded
    // address0 and address1 are directly reachable
    // address2 are reachable after tunneling
    PowerMockito.spy(NetworkUtils.class);
    PowerMockito.doReturn(new Pair<>(address0, process)).
        when(NetworkUtils.class, "establishSSHTunnelIfNeeded",
            eq(address0), anyString(), any(NetworkUtils.TunnelType.class), anyInt(),
            anyInt(), anyInt(), anyInt());
    PowerMockito.doReturn(new Pair<>(address1, process)).
        when(NetworkUtils.class, "establishSSHTunnelIfNeeded",
            eq(address1), anyString(), any(NetworkUtils.TunnelType.class), anyInt(),
            anyInt(), anyInt(), anyInt());
    PowerMockito.doReturn(new Pair<>(tunnelAddress, process)).
        when(NetworkUtils.class, "establishSSHTunnelIfNeeded",
            eq(address2), anyString(), any(NetworkUtils.TunnelType.class), anyInt(),
            anyInt(), anyInt(), anyInt());

    Pair<String, List<Process>> ret = ZkUtils.setupZkTunnel(config, tunnelConfig);

    // Assert with expected results
    String expectedConnectionString =
        String.format("%s,%s,%s",
            address0.toString(), address1.toString(), tunnelAddress.toString());
    Assert.assertEquals(expectedConnectionString, ret.first);
    Assert.assertEquals(3, ret.second.size());
    for (Process p : ret.second) {
      Assert.assertEquals(process, p);
    }
  }
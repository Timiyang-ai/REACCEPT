  @Test
  public void getMasterRpcAddresses() {
    AlluxioConfiguration conf =
        createConf(ImmutableMap.of(PropertyKey.MASTER_RPC_ADDRESSES, "host1:99,host2:100"));
    assertEquals(
        Arrays.asList(InetSocketAddress.createUnresolved("host1", 99),
            InetSocketAddress.createUnresolved("host2", 100)),
        ConfigurationUtils.getMasterRpcAddresses(conf));
  }
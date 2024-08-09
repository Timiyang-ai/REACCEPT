  @Test
  public void getJobMasterRpcAddresses() {
    AlluxioConfiguration conf =
        createConf(ImmutableMap.of(PropertyKey.JOB_MASTER_RPC_ADDRESSES, "host1:99,host2:100"));
    assertEquals(
        Arrays.asList(InetSocketAddress.createUnresolved("host1", 99),
            InetSocketAddress.createUnresolved("host2", 100)),
        ConfigurationUtils.getJobMasterRpcAddresses(conf));
  }
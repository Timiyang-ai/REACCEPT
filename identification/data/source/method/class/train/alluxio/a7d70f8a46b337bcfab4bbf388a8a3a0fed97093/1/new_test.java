  @Test
  public void registerNewConf() {
    ServerConfigurationStore configStore = createConfigStore();

    Map<Address, List<ConfigRecord>> confMap = configStore.getConfMap();

    assertTrue(confMap.containsKey(mAddressOne));
    assertTrue(confMap.containsKey(mAddressTwo));
  }
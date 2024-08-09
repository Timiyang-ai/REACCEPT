public synchronized void registerNewConf(Address address, List<ConfigProperty> configList) {
    Preconditions.checkNotNull(address, "address should not be null");
    Preconditions.checkNotNull(configList, "configuration list should not be null");
    // Instead of recording property name, we record property key.
    mConfMap.put(address, configList.stream().map(c -> new ConfigRecord()
        .setKey(PropertyKey.fromString(c.getName())).setSource(c.getSource())
        .setValue(c.getValue())).collect(Collectors.toList()));
    mLostNodes.remove(address);
    for (Runnable function : mChangeListeners) {
      function.run();
    }
  }
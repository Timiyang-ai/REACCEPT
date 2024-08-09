public void configure(ServerConfig serverConfig) {
    dbTypeMap.config(nativeUuidType, serverConfig.getDbUuid());
  }
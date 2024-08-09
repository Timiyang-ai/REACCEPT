public void configure(ServerConfig serverConfig) {
    dbTypeMap.config(nativeUuidType, serverConfig.getDbUuid());
    for (CustomDbTypeMapping mapping : serverConfig.getCustomTypeMappings()) {
      if (platformMatch(mapping.getPlatform())) {
        dbTypeMap.put(mapping.getType(), parse(mapping.getColumnDefinition()));
      }
    }
  }
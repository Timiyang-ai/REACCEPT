protected void configure(DbTypeConfig config, boolean allQuotedIdentifiers) {
    this.allQuotedIdentifiers = allQuotedIdentifiers;
    addGeoTypes(config.getGeometrySRID());
    configureIdType(config.getIdType());
    dbTypeMap.config(nativeUuidType, config.getDbUuid());
    for (CustomDbTypeMapping mapping : config.getCustomTypeMappings()) {
      if (platformMatch(mapping.getPlatform())) {
        dbTypeMap.put(mapping.getType(), parse(mapping.getColumnDefinition()));
      }
    }
  }
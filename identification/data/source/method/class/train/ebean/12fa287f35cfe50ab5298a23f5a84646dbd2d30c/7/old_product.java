public void configure(PlatformConfig config) {
    this.sequenceBatchSize = config.getDatabaseSequenceBatchSize();
    this.caseSensitiveCollation = config.isCaseSensitiveCollation();
    if (config.isUseMigrationStoredProcedures()) {
      useMigrationStoredProcedures = true;
    }
    configureIdType(config.getIdType());
    configure(config, config.isAllQuotedIdentifiers());
  }
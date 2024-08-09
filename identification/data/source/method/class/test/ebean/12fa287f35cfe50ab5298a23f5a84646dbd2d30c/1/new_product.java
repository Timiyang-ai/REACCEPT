public void configure(PlatformConfig config) {
    this.sequenceBatchSize = config.getDatabaseSequenceBatchSize();
    this.caseSensitiveCollation = config.isCaseSensitiveCollation();
    configureIdType(config.getIdType());
    configure(config, config.isAllQuotedIdentifiers());
  }
public void configure(PlatformConfig config) {
    this.sequenceBatchSize = config.getDatabaseSequenceBatchSize();
    configureIdType(config.getIdType());
    configure(config, config.isAllQuotedIdentifiers());
  }
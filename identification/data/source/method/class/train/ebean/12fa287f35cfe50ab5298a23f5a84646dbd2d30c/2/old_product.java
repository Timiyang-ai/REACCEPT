public void configure(ServerConfig config) {
    this.sequenceBatchSize = config.getDatabaseSequenceBatchSize();
    configureIdType(config.getIdType());
    configure(config.getDbTypeConfig(), config.isAllQuotedIdentifiers());
  }
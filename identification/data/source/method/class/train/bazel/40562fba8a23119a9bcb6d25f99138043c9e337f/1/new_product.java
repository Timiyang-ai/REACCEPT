static LcovMergerFlags parseFlags(String[] args) {
    LcovMergerFlags flags = new LcovMergerFlags();
    JCommander jCommander = new JCommander(flags);
    jCommander.setAllowParameterOverwriting(true);
    jCommander.setAcceptUnknownOptions(true);
    try {
      jCommander.parse(args);
    } catch (ParameterException e) {
      throw new IllegalArgumentException("Error parsing args", e);
    }
    if (flags.coverageDir == null && flags.reportsFile == null) {
      throw new IllegalArgumentException(
          "At least one of coverage_dir or reports_file should be specified.");
    }
    if (flags.coverageDir != null && flags.reportsFile != null) {
      logger.warning("Overriding --coverage_dir value in favor of --reports_file");
    }
    if (flags.outputFile == null) {
      throw new IllegalArgumentException("output_file was not specified.");
    }
    return flags;
  }
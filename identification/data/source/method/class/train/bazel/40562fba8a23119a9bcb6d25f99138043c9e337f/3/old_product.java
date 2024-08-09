static LcovMergerFlags parseFlags(String[] args) {
    ImmutableList.Builder<String> filterSources = new ImmutableList.Builder<>();
    String coverageDir = null;
    String reportsFile = null;
    String outputFile = null;

    for (String arg : args) {
      if (!arg.startsWith("--")) {
        throw new IllegalArgumentException("Argument (" + arg + ") should start with --");
      }
      String[] parts = arg.substring(2).split("=", 2);
      if (parts.length != 2) {
        throw new IllegalArgumentException("There should be = in argument (" + arg + ")");
      }
      switch (parts[0]) {
        case "coverage_dir":
          coverageDir = parts[1];
          break;
        case "reports_file":
          reportsFile = parts[1];
          break;
        case "output_file":
          outputFile = parts[1];
          break;
        case "filter_sources":
          filterSources.add(parts[1]);
          break;
        default:
          throw new IllegalArgumentException("Unknown flag " + arg);
      }
    }

    if (coverageDir == null && reportsFile == null) {
      throw new IllegalArgumentException(
          "At least one of --coverage_dir or --reports_file should be specified.");
    }
    if (coverageDir != null && reportsFile != null) {
      throw new IllegalArgumentException(
          "Only one of --coverage_dir or --reports_file must be specified.");
    }
    if (outputFile == null) {
      throw new IllegalArgumentException("--output_file was not specified.");
    }
    return new AutoValue_LcovMergerFlags(
        coverageDir, reportsFile, outputFile, filterSources.build());
  }
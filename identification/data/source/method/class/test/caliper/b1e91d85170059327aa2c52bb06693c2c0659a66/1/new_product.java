@Override ImmutableSet<String> getExtraCommandLineArgs() {
    String agentJar = options.get("allocationAgentJar");
    if (Strings.isNullOrEmpty(agentJar)) {
      try {
        Optional<File> instrumentJar = findAllocationInstrumentJarOnClasspath();
        // TODO(gak): bundle up the allocation jar and unpack it if it's not on the classpath
        if (instrumentJar.isPresent()) {
          agentJar = instrumentJar.get().getAbsolutePath();
        }
      } catch (IOException e) {
        logger.log(SEVERE,
            "An exception occurred trying to locate the allocation agent jar on the classpath", e);
      }
    }
    // TODO(schmoe): what can we do to verify that agentJar is correct? Or to get a nicer
    // error message when it's not?
    if (Strings.isNullOrEmpty(agentJar) || !new File(agentJar).exists()) {
      throw new IllegalStateException("Can't find required allocationinstrumenter agent jar");
    }
    // Add microbenchmark args to minimize differences in the output
    return new ImmutableSet.Builder<String>()
        .addAll(super.getExtraCommandLineArgs())
        .add("-javaagent:" + agentJar)
        .build();
  }
@Override ImmutableSet<String> getExtraCommandLineArgs() {
    String agentJar = options.get(ALLOCATION_AGENT_JAR_OPTION);
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
    if (Strings.isNullOrEmpty(agentJar) || !new File(agentJar).exists()) {
      throw new IllegalStateException("Can't find required allocationinstrumenter agent jar");
    }
    // Add microbenchmark args to minimize differences in the output
    return new ImmutableSet.Builder<String>()
        .addAll(super.getExtraCommandLineArgs())
        .add("-javaagent:" + agentJar)
        // Some environments rename files and use symlinks to improve resource caching,
        // if the agent jar path is actually a symlink it will prevent the agent from finding itself
        // and adding itself to the bootclasspath, so we do it manually here.
        .add("-Xbootclasspath/a:" + agentJar)
        .build();
  }
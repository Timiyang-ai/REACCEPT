@Override Iterable<String> getExtraCommandLineArgs() {
    String agentJar = options.get("allocationAgentJar");
    // TODO(schmoe): what can we do to verify that agentJar is correct? Or to get a nicer
    // error message when it's not?
    if (agentJar == null || !new File(agentJar).exists()) {
      throw new IllegalStateException("Can't find required allocationinstrumenter agent jar");
    }
    return Iterables.concat(super.getExtraCommandLineArgs(),
        ImmutableList.of("-javaagent:" + agentJar));
  }
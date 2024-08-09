public VmConfig getDefaultVmConfig() {
    return new Builder(new File(System.getProperty("java.home")))
        .addAllOptions(Collections2.filter(ManagementFactory.getRuntimeMXBean().getInputArguments(),
            new Predicate<String>() {
              @Override public boolean apply(@Nullable String input) {
                // Exclude the -agentlib:jdwp param which configures the socket debugging protocol.
                // If this is set in the parent VM we do not want it to be inherited by the child 
                // VM.  If it is, the child will die immediately on startup because it will fail to
                // bind to the debug port (because the parent VM is already bound to it).
                return !input.startsWith("-agentlib:jdwp");
              }
            }))
        // still incorporate vm.args
        .addAllOptions(getArgs(subgroupMap(properties, "vm")))
        .build();
  }
public VmConfig getDefaultVmConfig() {
    return new Builder(new File(System.getProperty("java.home")))
        .addAllOptions(ManagementFactory.getRuntimeMXBean().getInputArguments())
        // still incorporate vm.args
        .addAllOptions(getArgs(subgroupMap(properties, "vm")))
        .build();
  }
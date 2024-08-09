public VmConfig getDefaultVmConfig(Platform platform) {
    return new VmConfig.Builder(platform, platform.defaultVmHomeDir())
        .addAllOptions(platform.inputArguments())
        // still incorporate vm.args
        .addAllOptions(getArgs(subgroupMap(properties, "vm")))
        .build();
  }
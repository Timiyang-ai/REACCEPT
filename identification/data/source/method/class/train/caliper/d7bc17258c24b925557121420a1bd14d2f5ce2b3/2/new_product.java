public VmConfig getDefaultVmConfig(Platform platform) {
    return VmConfig.builder()
        .name("default")
        .platform(platform)
        .type(platform.vmType())
        .home(platform.defaultVmHomeDir().getPath())
        .addAllArgs(platform.inputArguments())
        .addAllArgs(getVmArgs())
        .build();
  }
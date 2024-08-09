  @Test
  public void getDefaultVmConfig() throws Exception {
    CaliperConfig configuration =
        new CaliperConfig(
            ImmutableMap.of(
                "device.local.type", "local",
                "vm.args", "-very -special=args"));
    Device device = LocalDevice.builder().caliperConfig(configuration).build();
    VmConfig defaultVmConfig = device.defaultVmConfig();
    assertEquals(System.getProperty("java.home"), defaultVmConfig.home().get());
    ImmutableList<String> expectedArgs =
        new ImmutableList.Builder<String>()
            .addAll(ManagementFactory.getRuntimeMXBean().getInputArguments())
            .add("-very")
            .add("-special=args")
            .build();
    assertEquals(expectedArgs, defaultVmConfig.args());
  }
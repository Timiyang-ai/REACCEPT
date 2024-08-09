  @Test
  public void getExtraCommandLineArgs() throws Exception {
    AllocationInstrument instrument = new AllocationInstrument();
    File fakeJar = File.createTempFile("fake", "jar");
    fakeJar.deleteOnExit();
    instrument.setOptions(ImmutableMap.of("allocationAgentJar", fakeJar.getAbsolutePath()));
    ImmutableSet<String> expected =
        ImmutableSet.of(
            "-Xint",
            "-javaagent:" + fakeJar.getAbsolutePath(),
            "-Xbootclasspath/a:" + fakeJar.getAbsolutePath());
    VmConfig vmConfig =
        VmConfig.builder()
            .name("foo")
            .type(VmType.JVM)
            .home(System.getProperty("java.home"))
            .build();
    assertEquals(expected, instrument.getExtraCommandLineArgs(vmConfig));
    fakeJar.delete();
  }
  @Test
  public void createProfileStream() {

    ProfilingConfig profilingConfig = new ProfilingConfig();
    profilingConfig.setDirectory("target/profiling");

    DefaultProfileHandler handler = new DefaultProfileHandler(profilingConfig);

    assertNotNull(handler.createProfileStream(12));
    assertNull(handler.createProfileStream(0));
  }
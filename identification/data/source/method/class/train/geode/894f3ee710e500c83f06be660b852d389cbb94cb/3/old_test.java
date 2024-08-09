  @Test
  public void startup_emptyParams() {
    // arrange
    String stringId = "my string";
    Object[] params = new Object[0];
    StartupStatus startupStatus = new StartupStatus();

    // act
    startupStatus.startup(stringId, params);

    // assert (does not throw)
    assertThat(getStartupListener())
        .isNull();
  }
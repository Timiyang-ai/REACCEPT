  @Test public void findPlatform_jre8Wins() throws ClassNotFoundException {
    mockStatic(Class.class);
    when(Class.forName("java.io.UncheckedIOException"))
      .thenReturn(null);

    assertThat(Platform.findPlatform())
      .isInstanceOf(Platform.Jre8.class);
  }
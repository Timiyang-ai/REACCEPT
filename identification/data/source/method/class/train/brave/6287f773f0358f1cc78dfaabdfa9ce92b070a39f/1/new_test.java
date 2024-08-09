  @Test public void error_customizer() {
    parser.error(new RuntimeException("this cake is a lie"), customizer);

    verify(customizer).tag("error", "this cake is a lie");
  }
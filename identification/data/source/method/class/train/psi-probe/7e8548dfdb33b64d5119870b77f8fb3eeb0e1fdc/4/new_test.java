  @Test
  public void contextInitializedTest() {
    listener.contextInitialized(event);

    new Verifications() {
      {
        ImageIO.getCacheDirectory();
        times = 1;
      }
    };
  }
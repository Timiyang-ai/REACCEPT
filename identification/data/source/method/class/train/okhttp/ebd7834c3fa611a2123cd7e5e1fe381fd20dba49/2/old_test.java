  @Test public void forJavaName_acceptsArbitraryStrings() {
    // Shouldn't throw.
    forJavaName("example CipherSuite name that is not in the whitelist");
  }
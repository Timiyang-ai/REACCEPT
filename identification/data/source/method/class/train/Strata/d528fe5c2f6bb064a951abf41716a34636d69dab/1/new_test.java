  @Test
  public void test_ofResource_directory() {
    ExampleMarketDataBuilder builder = ExampleMarketDataBuilder.ofResource(EXAMPLE_MARKET_DATA_CLASSPATH_ROOT);
    assertBuilder(builder);
  }
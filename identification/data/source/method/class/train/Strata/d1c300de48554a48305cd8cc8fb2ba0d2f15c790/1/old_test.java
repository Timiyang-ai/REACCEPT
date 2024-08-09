  @Test
  public void test_load_failures() {
    TradeCsvLoader test = TradeCsvLoader.standard();
    ValueWithFailures<List<Trade>> trades = test.load(FILE);

    assertThat(trades.getFailures().size()).as(trades.getFailures().toString()).isEqualTo(0);
  }
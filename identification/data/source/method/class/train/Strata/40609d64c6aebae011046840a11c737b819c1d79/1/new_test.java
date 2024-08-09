  @Test
  @SuppressWarnings("deprecation")
  public void test_parseLightweight() {
    PositionCsvLoader test = PositionCsvLoader.standard();
    ValueWithFailures<List<SecurityPosition>> trades = test.parseLightweight(ImmutableList.of(FILE.getCharSource()));
    List<SecurityPosition> filtered = trades.getValue();
    assertLightweight(filtered);
  }
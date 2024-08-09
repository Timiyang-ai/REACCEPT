  @Test
  public void test_normalize_sell_double() {
    assertThat(BuySell.SELL.normalize(1d)).isCloseTo(-1d, offset(0d));
    assertThat(BuySell.SELL.normalize(0d)).isCloseTo(0d, offset(0d));
    assertThat(BuySell.SELL.normalize(-0d)).isCloseTo(0d, offset(0d));
    assertThat(BuySell.SELL.normalize(-1d)).isCloseTo(-1d, offset(0d));
  }
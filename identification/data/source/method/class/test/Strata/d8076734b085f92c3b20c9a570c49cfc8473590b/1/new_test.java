  @Test
  public void test_calculateMonetaryValue() {
    // CME-ED, 1bp = $25
    SecurityPriceInfo test = SecurityPriceInfo.of(0.005, CurrencyAmount.of(USD, 12.50), 1);
    assertThat(test.calculateMonetaryValue(1, 98)).isEqualTo(245_000d);
    assertThat(test.calculateMonetaryValue(1, 98.02)).isEqualTo(245_050d);
    // quantity is simple multiplier
    assertThat(test.calculateMonetaryValue(2, 98)).isEqualTo(2 * 245_000d);
    assertThat(test.calculateMonetaryValue(3, 98)).isEqualTo(3 * 245_000d);
    // contract size is simple multiplier
    SecurityPriceInfo test2 = SecurityPriceInfo.of(0.005, CurrencyAmount.of(USD, 12.50), 2);
    assertThat(test2.calculateMonetaryValue(1, 98)).isEqualTo(2 * 245_000d);
  }
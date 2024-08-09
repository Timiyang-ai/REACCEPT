  @Test
  public void test_calculateMonetaryAmount1() {
    // CME-ED, 1bp = $25
    SecurityPriceInfo test = SecurityPriceInfo.of(0.005, CurrencyAmount.of(USD, 12.50), 1);
    assertThat(test.calculateMonetaryAmount(1, 98)).isEqualTo(CurrencyAmount.of(USD, 245_000));
    assertThat(test.calculateMonetaryAmount(1, 98.02)).isEqualTo(CurrencyAmount.of(USD, 245_050));
    // quantity is simple multiplier
    assertThat(test.calculateMonetaryAmount(2, 98)).isEqualTo(CurrencyAmount.of(USD, 2 * 245_000));
    assertThat(test.calculateMonetaryAmount(3, 98)).isEqualTo(CurrencyAmount.of(USD, 3 * 245_000));
    // contract size is simple multiplier
    SecurityPriceInfo test2 = SecurityPriceInfo.of(0.005, CurrencyAmount.of(USD, 12.50), 2);
    assertThat(test2.calculateMonetaryAmount(1, 98)).isEqualTo(CurrencyAmount.of(USD, 2 * 245_000));
  }
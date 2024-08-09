  @Test
  public void test_withCurrency() {
    ZeroRateSensitivity base = ZeroRateSensitivity.of(GBP, YEARFRAC, 32d);
    assertThat(base.withCurrency(GBP)).isSameAs(base);
    assertThat(base.withCurrency(USD)).isEqualTo(ZeroRateSensitivity.of(GBP, YEARFRAC, USD, 32d));
  }
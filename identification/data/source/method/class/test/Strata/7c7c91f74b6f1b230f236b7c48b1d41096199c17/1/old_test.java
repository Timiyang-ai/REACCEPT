  @Test
  public void test_withCurrency() {
    CreditCurveZeroRateSensitivity base = CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, USD, YEAR_FRACTION, VALUE);
    assertThat(base.withCurrency(USD)).isSameAs(base);
    assertThat(base.withCurrency(GBP)).isEqualTo(CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, USD, YEAR_FRACTION, GBP, VALUE));
  }
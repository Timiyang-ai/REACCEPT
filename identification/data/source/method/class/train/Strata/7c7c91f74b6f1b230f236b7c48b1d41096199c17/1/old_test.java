  @Test
  public void test_convertedTo() {
    double sensi = 32d;
    CreditCurveZeroRateSensitivity base = CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, GBP, YEAR_FRACTION, sensi);
    double rate = 1.5d;
    FxMatrix matrix = FxMatrix.of(CurrencyPair.of(GBP, USD), rate);
    CreditCurveZeroRateSensitivity test1 = base.convertedTo(USD, matrix);
    CreditCurveZeroRateSensitivity expected =
        CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, GBP, YEAR_FRACTION, USD, rate * sensi);
    assertThat(test1).isEqualTo(expected);
    CreditCurveZeroRateSensitivity test2 = base.convertedTo(GBP, matrix);
    assertThat(test2).isEqualTo(base);
  }
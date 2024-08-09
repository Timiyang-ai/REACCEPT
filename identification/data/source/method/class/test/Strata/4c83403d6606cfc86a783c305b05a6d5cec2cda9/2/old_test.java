  @Test
  public void test_forwardFxRate() {
    // forward rate is computed by discounting for any RatesProvider input.
    FxRate computed = PRICER.forwardFxRate(FWD, PROVIDER);
    double df1 = PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double df2 = PROVIDER.discountFactor(KRW, PAYMENT_DATE);
    double spot = PROVIDER.fxRate(USD, KRW);
    FxRate expected = FxRate.of(USD, KRW, spot * df1 / df2);
    assertThat(computed).isEqualTo(expected);
  }
  @Test
  public void test_presentValueWithZSpread_standard() {
    CurrencyAmount computed = PRICER.presentValueWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, CONTINUOUS, 0);
    double expected1 = QUANTITY * PRODUCT_PRICER.presentValueWithZSpread(
        RPRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, SETTLEMENT_STANDARD, Z_SPREAD, CONTINUOUS, 0).getAmount();
    double df = ISSUER_RATES_PROVIDER.repoCurveDiscountFactors(SECURITY_ID, LEGAL_ENTITY, USD)
        .discountFactor(SETTLEMENT_STANDARD);
    double expected2 = df * PERIOD_PRICER.forecastValue(SETTLE_PERIOD_STANDARD, RATES_PROVIDER);
    assertThat(computed.getAmount()).isCloseTo(expected1 + expected2, offset(NOTIONAL * QUANTITY * TOL));
  }
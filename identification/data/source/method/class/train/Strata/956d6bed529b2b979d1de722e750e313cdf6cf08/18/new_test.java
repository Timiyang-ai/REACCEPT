  @Test
  public void test_presentValue_standard() {
    CurrencyAmount computed = PRICER.presentValue(TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER);
    double expected1 = PRODUCT_PRICER.presentValue(
        RPRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, SETTLEMENT_STANDARD).getAmount() * QUANTITY;
    double df = ISSUER_RATES_PROVIDER.repoCurveDiscountFactors(SECURITY_ID, LEGAL_ENTITY, USD)
        .discountFactor(SETTLEMENT_STANDARD);
    double expected2 = df * PERIOD_PRICER.forecastValue(SETTLE_PERIOD_STANDARD, RATES_PROVIDER);
    assertThat(computed.getAmount()).isCloseTo(expected1 + expected2, offset(NOTIONAL * QUANTITY * TOL));
  }
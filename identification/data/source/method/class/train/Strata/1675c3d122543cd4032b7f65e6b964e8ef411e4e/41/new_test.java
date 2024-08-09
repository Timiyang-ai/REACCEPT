  @Test
  public void test_presentValueFromCleanPriceWithZSpread_standard() {
    CurrencyAmount computed = PRICER.presentValueFromCleanPriceWithZSpread(TRADE_STANDARD, RATES_PROVIDER,
        ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    CurrencyAmount netAmount = PRICER.netAmount(TRADE_STANDARD, RATES_PROVIDER);
    double df1 = ISSUER_RATES_PROVIDER.repoCurveDiscountFactors(SECURITY_ID, LEGAL_ENTITY, USD)
        .discountFactor(SETTLEMENT_STANDARD);
    double df2 = ISSUER_RATES_PROVIDER.repoCurveDiscountFactors(SECURITY_ID, LEGAL_ENTITY, USD)
        .discountFactor(SETTLEMENT_STANDARD);
    double expected1 = netAmount.getAmount() * df1;
    double expected2 = QUANTITY * df2 * PRICER.forecastValueStandardFromCleanPrice(
        RPRODUCT, RATES_PROVIDER, SETTLEMENT_STANDARD, TRADE_PRICE).getAmount();
    assertThat(computed.getAmount()).isCloseTo(expected1 + expected2, offset(NOTIONAL * QUANTITY * TOL));
  }
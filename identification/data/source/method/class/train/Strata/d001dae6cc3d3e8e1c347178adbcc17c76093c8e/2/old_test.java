  @Test
  public void test_presentValueWithZSpread() {
    CurrencyAmount computed = PRICER.presentValueWithZSpread(
        PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    double expected = PERIOD_PRICER.presentValueWithZSpread(PRODUCT.getNominalPayment(), RATES_PROVIDER,
        ISSUER_DISCOUNT_FACTORS, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    int size = PRODUCT.getPeriodicPayments().size();
    for (int i = 16; i < size; ++i) {
      CapitalIndexedBondPaymentPeriod payment = PRODUCT.getPeriodicPayments().get(i);
      expected += PERIOD_PRICER.presentValueWithZSpread(
          payment, RATES_PROVIDER, ISSUER_DISCOUNT_FACTORS, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    }
    assertThat(computed.getAmount()).isCloseTo(expected, offset(TOL * NOTIONAL));
  }
  @Test
  public void test_presentValue() {
    CurrencyAmount computed = PRICER.presentValue(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER);
    double expected = PERIOD_PRICER.presentValue(PRODUCT.getNominalPayment(), RATES_PROVIDER, ISSUER_DISCOUNT_FACTORS);
    int size = PRODUCT.getPeriodicPayments().size();
    for (int i = 16; i < size; ++i) {
      CapitalIndexedBondPaymentPeriod payment = PRODUCT.getPeriodicPayments().get(i);
      expected += PERIOD_PRICER.presentValue(payment, RATES_PROVIDER, ISSUER_DISCOUNT_FACTORS);
    }
    assertThat(computed.getAmount()).isCloseTo(expected, offset(TOL * NOTIONAL));
  }
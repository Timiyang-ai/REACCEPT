  @Test
  public void test_presentValue_single() {
    SimpleRatesProvider prov = createProvider(VAL_DATE);

    double pvExpected = RATE_1 * ACCRUAL_FACTOR_1 * NOTIONAL_100 * DISCOUNT_FACTOR;
    double pvComputed = DiscountingRatePaymentPeriodPricer.DEFAULT.presentValue(PAYMENT_PERIOD_1, prov);
    assertThat(pvComputed).isCloseTo(pvExpected, offset(TOLERANCE_PV));
  }
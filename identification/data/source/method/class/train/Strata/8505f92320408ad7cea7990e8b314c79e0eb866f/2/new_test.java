  @Test
  public void test_accruedInterest_firstAccrualPeriod() {
    LocalDate valDate = PAYMENT_PERIOD_FULL_GS.getStartDate().plusDays(7);
    SimpleRatesProvider prov = createProvider(valDate);

    double partial = PAYMENT_PERIOD_FULL_GS.getDayCount().yearFraction(ACCRUAL_PERIOD_1_GS.getStartDate(), valDate);
    double fraction = partial / ACCRUAL_FACTOR_1;
    double expected = ((RATE_1 * GEARING + SPREAD) * ACCRUAL_FACTOR_1 * NOTIONAL_100) * fraction;

    double computed = DiscountingRatePaymentPeriodPricer.DEFAULT.accruedInterest(PAYMENT_PERIOD_FULL_GS, prov);
    assertThat(computed).isCloseTo(expected, offset(TOLERANCE_PV));
  }
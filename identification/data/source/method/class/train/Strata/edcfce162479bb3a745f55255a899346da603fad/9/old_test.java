  @Test
  public void test_explainPresentValue_single() {
    RatesProvider prov = createProvider(VAL_DATE);

    DiscountingRatePaymentPeriodPricer test = DiscountingRatePaymentPeriodPricer.DEFAULT;
    ExplainMapBuilder builder = ExplainMap.builder();
    test.explainPresentValue(PAYMENT_PERIOD_1, prov, builder);
    ExplainMap explain = builder.build();

    Currency currency = PAYMENT_PERIOD_1.getCurrency();
    double ua = RATE_1 * ACCRUAL_FACTOR_1;
    double fv = ua * NOTIONAL_100;
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("RatePaymentPeriod");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(PAYMENT_PERIOD_1.getPaymentDate());
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.NOTIONAL).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.NOTIONAL).get().getAmount()).isCloseTo(NOTIONAL_100, offset(TOLERANCE_PV));
    assertThat(explain.get(ExplainKey.TRADE_NOTIONAL).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.TRADE_NOTIONAL).get().getAmount()).isCloseTo(NOTIONAL_100, offset(TOLERANCE_PV));
    assertThat(explain.get(ExplainKey.COMPOUNDING).get()).isEqualTo(PAYMENT_PERIOD_1.getCompoundingMethod());
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isCloseTo(DISCOUNT_FACTOR, offset(TOLERANCE_PV));
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(fv, offset(TOLERANCE_PV));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(fv * DISCOUNT_FACTOR, offset(TOLERANCE_PV));

    assertThat(explain.get(ExplainKey.ACCRUAL_PERIODS).get()).hasSize(1);
    ExplainMap explainAccrual = explain.get(ExplainKey.ACCRUAL_PERIODS).get().get(0);
    RateAccrualPeriod ap = PAYMENT_PERIOD_1.getAccrualPeriods().get(0);
    int daysBetween = (int) DAYS.between(ap.getStartDate(), ap.getEndDate());
    assertThat(explainAccrual.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("AccrualPeriod");
    assertThat(explainAccrual.get(ExplainKey.START_DATE).get()).isEqualTo(ap.getStartDate());
    assertThat(explainAccrual.get(ExplainKey.UNADJUSTED_START_DATE).get()).isEqualTo(ap.getUnadjustedStartDate());
    assertThat(explainAccrual.get(ExplainKey.END_DATE).get()).isEqualTo(ap.getEndDate());
    assertThat(explainAccrual.get(ExplainKey.UNADJUSTED_END_DATE).get()).isEqualTo(ap.getUnadjustedEndDate());
    assertThat(explainAccrual.get(ExplainKey.ACCRUAL_YEAR_FRACTION).get()).isEqualTo(ap.getYearFraction());
    assertThat(explainAccrual.get(ExplainKey.ACCRUAL_DAYS).get()).isEqualTo((Integer) daysBetween);
    assertThat(explainAccrual.get(ExplainKey.GEARING).get()).isCloseTo(ap.getGearing(), offset(TOLERANCE_PV));
    assertThat(explainAccrual.get(ExplainKey.SPREAD).get()).isCloseTo(ap.getSpread(), offset(TOLERANCE_PV));
    assertThat(explainAccrual.get(ExplainKey.FIXED_RATE).get()).isCloseTo(RATE_1, offset(TOLERANCE_PV));
    assertThat(explainAccrual.get(ExplainKey.PAY_OFF_RATE).get()).isCloseTo(RATE_1, offset(TOLERANCE_PV));
    assertThat(explainAccrual.get(ExplainKey.UNIT_AMOUNT).get()).isCloseTo(ua, offset(TOLERANCE_PV));
  }
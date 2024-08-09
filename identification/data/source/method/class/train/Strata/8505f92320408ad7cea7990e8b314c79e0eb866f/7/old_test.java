  @Test
  public void test_explainPresentValue() {
    ExplainMapBuilder builder = ExplainMap.builder();
    PRICER.explainPresentValue(PAYMENT_PERIOD, ISSUER_CURVE, builder);
    ExplainMap explain = builder.build();
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("FixedCouponBondPaymentPeriod");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(PAYMENT_PERIOD.getPaymentDate());
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(PAYMENT_PERIOD.getCurrency());
    assertThat(explain.get(ExplainKey.START_DATE).get()).isEqualTo(START_ADJUSTED);
    assertThat(explain.get(ExplainKey.UNADJUSTED_START_DATE).get()).isEqualTo(START);
    assertThat(explain.get(ExplainKey.END_DATE).get()).isEqualTo(END_ADJUSTED);
    assertThat(explain.get(ExplainKey.UNADJUSTED_END_DATE).get()).isEqualTo(END);
    assertThat(explain.get(ExplainKey.DAYS).get().intValue()).isEqualTo((int) DAYS.between(START_ADJUSTED, END_ADJUSTED));
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isEqualTo(DSC_FACTORS.discountFactor(END_ADJUSTED));
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(FIXED_RATE * NOTIONAL * YEAR_FRACTION, offset(NOTIONAL * TOL));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(FIXED_RATE * NOTIONAL * YEAR_FRACTION * DSC_FACTORS.discountFactor(END_ADJUSTED), offset(NOTIONAL * TOL));
  }
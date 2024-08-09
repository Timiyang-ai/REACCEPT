  @Test
  public void test_explainPresentValue() {
    ExplainMapBuilder builder = ExplainMap.builder();
    PRICER.explainPresentValue(PERIOD_INTERP, IRP_BEFORE_START, ICDF_BEFORE_START, builder);
    ExplainMap explain = builder.build();
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("CapitalIndexedBondPaymentPeriod");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(PERIOD_INTERP.getPaymentDate());
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(PERIOD_INTERP.getCurrency());
    assertThat(explain.get(ExplainKey.START_DATE).get()).isEqualTo(START);
    assertThat(explain.get(ExplainKey.UNADJUSTED_START_DATE).get()).isEqualTo(START_UNADJ);
    assertThat(explain.get(ExplainKey.END_DATE).get()).isEqualTo(END);
    assertThat(explain.get(ExplainKey.UNADJUSTED_END_DATE).get()).isEqualTo(END_UNADJ);
    assertThat(explain.get(ExplainKey.DAYS).get().intValue()).isEqualTo((int) DAYS.between(START_UNADJ, END_UNADJ));
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isEqualTo(ICDF_BEFORE_START.discountFactor(END));
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(PRICER.forecastValue(PERIOD_INTERP, IRP_BEFORE_START), offset(NOTIONAL * TOL));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(PRICER.presentValue(PERIOD_INTERP, IRP_BEFORE_START, ICDF_BEFORE_START), offset(NOTIONAL * TOL));
  }
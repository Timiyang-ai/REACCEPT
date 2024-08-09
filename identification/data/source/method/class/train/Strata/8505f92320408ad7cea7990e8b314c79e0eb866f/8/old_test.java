  @Test
  public void test_explainPresentValue() {
    RatesProvider prov = createProvider(VAL_DATE);

    ExplainMapBuilder builder = ExplainMap.builder();
    PRICER.explainPresentValue(PERIOD, prov, builder);
    ExplainMap explain = builder.build();

    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("KnownAmountPaymentPeriod");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(PERIOD.getPaymentDate());
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(PERIOD.getCurrency());
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isCloseTo(DISCOUNT_FACTOR, offset(TOLERANCE_PV));

    int daysBetween = (int) DAYS.between(DATE_1, DATE_2);
    assertThat(explain.get(ExplainKey.START_DATE).get()).isEqualTo(PERIOD.getStartDate());
    assertThat(explain.get(ExplainKey.UNADJUSTED_START_DATE).get()).isEqualTo(PERIOD.getUnadjustedStartDate());
    assertThat(explain.get(ExplainKey.END_DATE).get()).isEqualTo(PERIOD.getEndDate());
    assertThat(explain.get(ExplainKey.UNADJUSTED_END_DATE).get()).isEqualTo(PERIOD.getUnadjustedEndDate());
    assertThat(explain.get(ExplainKey.DAYS).get()).isEqualTo((Integer) daysBetween);

    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(PERIOD.getCurrency());
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(AMOUNT_1000, offset(TOLERANCE_PV));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getCurrency()).isEqualTo(PERIOD.getCurrency());
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(AMOUNT_1000 * DISCOUNT_FACTOR, offset(TOLERANCE_PV));
  }
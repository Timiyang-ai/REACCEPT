  @Test
  public void test_explainPresentValue() {
    ExplainMap explain = LEG_PRICER.explainPresentValue(CAP_LEG, RATES_PROVIDER, VOLATILITIES);
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("CmsLeg");
    assertThat(explain.get(ExplainKey.PAY_RECEIVE).get().toString()).isEqualTo("Receive");
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get().getCode()).isEqualTo("EUR");
    assertThat(explain.get(ExplainKey.START_DATE).get()).isEqualTo(LocalDate.of(2015, 10, 21));
    assertThat(explain.get(ExplainKey.END_DATE).get()).isEqualTo(LocalDate.of(2020, 10, 21));
    assertThat(explain.get(ExplainKey.INDEX).get().toString()).isEqualTo("EUR-EURIBOR-1100-5Y");
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isEqualTo(39728.51321029542);
    
    List<ExplainMap> paymentPeriods = explain.get(ExplainKey.PAYMENT_PERIODS).get();
    assertThat(paymentPeriods).hasSize(5);
    //Test First Period
    ExplainMap cmsPeriod0 = paymentPeriods.get(0);
    assertThat(cmsPeriod0.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("CmsCapletPeriod");
    assertThat(cmsPeriod0.get(ExplainKey.STRIKE_VALUE).get()).isEqualTo(0.0125d);
    assertThat(cmsPeriod0.get(ExplainKey.NOTIONAL).get().getAmount()).isEqualTo(1000000d);
    assertThat(cmsPeriod0.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(LocalDate.of(2016, 10, 21));
    assertThat(cmsPeriod0.get(ExplainKey.DISCOUNT_FACTOR).get()).isEqualTo(0.9820085531995826d);
    assertThat(cmsPeriod0.get(ExplainKey.START_DATE).get()).isEqualTo(LocalDate.of(2015, 10, 21));
    assertThat(cmsPeriod0.get(ExplainKey.END_DATE).get()).isEqualTo(LocalDate.of(2016, 10, 21));
    assertThat(cmsPeriod0.get(ExplainKey.FIXING_DATE).get()).isEqualTo(LocalDate.of(2015, 10, 19));
    assertThat(cmsPeriod0.get(ExplainKey.ACCRUAL_YEAR_FRACTION).get()).isEqualTo(1.0166666666666666d);
    double forwardSwapRate = PRICER_SWAP.parRate(CAP_LEG.getCmsPeriods().get(0).getUnderlyingSwap(), RATES_PROVIDER);
    assertThat(cmsPeriod0.get(ExplainKey.FORWARD_RATE).get()).isEqualTo(forwardSwapRate);
    CurrencyAmount pv = PERIOD_PRICER.presentValue(CAP_LEG.getCmsPeriods().get(0), RATES_PROVIDER, VOLATILITIES);
    assertThat(cmsPeriod0.get(ExplainKey.PRESENT_VALUE).get()).isEqualTo(pv);
  }
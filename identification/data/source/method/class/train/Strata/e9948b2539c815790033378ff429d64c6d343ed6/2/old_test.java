  @Test
  public void test_explainPresentValue() {
    ExplainMapBuilder builder = ExplainMap.builder();
    PRICER.explainPresentValue(FLOORLET, RATES_PROVIDER, VOLATILITIES, builder);
    ExplainMap explain = builder.build();
    //Test a CMS Floorlet Period.
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("CmsFloorletPeriod");
    assertThat(explain.get(ExplainKey.STRIKE_VALUE).get()).isEqualTo(0.04d);
    assertThat(explain.get(ExplainKey.NOTIONAL).get().getAmount()).isEqualTo(10000000d);
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(LocalDate.of(2021, 4, 28));
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isEqualTo(0.8518053333230845d);
    assertThat(explain.get(ExplainKey.START_DATE).get()).isEqualTo(LocalDate.of(2020, 4, 28));
    assertThat(explain.get(ExplainKey.END_DATE).get()).isEqualTo(LocalDate.of(2021, 4, 28));
    assertThat(explain.get(ExplainKey.FIXING_DATE).get()).isEqualTo(LocalDate.of(2020, 4, 24));
    assertThat(explain.get(ExplainKey.ACCRUAL_YEAR_FRACTION).get()).isEqualTo(1.0138888888888888d);
    double forwardSwapRate = PRICER_SWAP.parRate(FLOORLET.getUnderlyingSwap(), RATES_PROVIDER);
    assertThat(explain.get(ExplainKey.FORWARD_RATE).get()).isEqualTo(forwardSwapRate);
    CurrencyAmount pv = PRICER.presentValue(FLOORLET, RATES_PROVIDER, VOLATILITIES);
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get()).isEqualTo(pv);
    double adjustedForwardRate = PRICER.adjustedForwardRate(FLOORLET, RATES_PROVIDER, VOLATILITIES);
    assertThat(explain.get(ExplainKey.CONVEXITY_ADJUSTED_RATE).get()).isEqualTo(adjustedForwardRate);
    
  }
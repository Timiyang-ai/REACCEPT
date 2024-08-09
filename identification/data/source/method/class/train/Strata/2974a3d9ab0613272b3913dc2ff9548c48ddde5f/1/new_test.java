  @Test
  public void test_explainPresentValue_ISDA() {
    ResolvedFra fraExp = RFRA;
    SimpleRatesProvider prov = createProvider(fraExp);

    DiscountingFraProductPricer test = DiscountingFraProductPricer.DEFAULT;
    CurrencyAmount fvExpected = test.forecastValue(fraExp, prov);
    CurrencyAmount pvExpected = test.presentValue(fraExp, prov);

    ExplainMap explain = test.explainPresentValue(fraExp, prov);
    Currency currency = fraExp.getCurrency();
    int daysBetween = (int) DAYS.between(fraExp.getStartDate(), fraExp.getEndDate());
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("FRA");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(fraExp.getPaymentDate());
    assertThat(explain.get(ExplainKey.START_DATE).get()).isEqualTo(fraExp.getStartDate());
    assertThat(explain.get(ExplainKey.END_DATE).get()).isEqualTo(fraExp.getEndDate());
    assertThat(explain.get(ExplainKey.ACCRUAL_YEAR_FRACTION).get()).isEqualTo(fraExp.getYearFraction());
    assertThat(explain.get(ExplainKey.DAYS).get()).isEqualTo((Integer) (int) daysBetween);
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.NOTIONAL).get().getAmount()).isCloseTo(fraExp.getNotional(), offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.TRADE_NOTIONAL).get().getAmount()).isCloseTo(fraExp.getNotional(), offset(TOLERANCE));

    assertThat(explain.get(ExplainKey.OBSERVATIONS).get()).hasSize(1);
    ExplainMap explainObs = explain.get(ExplainKey.OBSERVATIONS).get().get(0);
    IborRateComputation floatingRate = (IborRateComputation) fraExp.getFloatingRate();
    assertThat(explainObs.get(ExplainKey.INDEX).get()).isEqualTo(floatingRate.getIndex());
    assertThat(explainObs.get(ExplainKey.FIXING_DATE).get()).isEqualTo(floatingRate.getFixingDate());
    assertThat(explainObs.get(ExplainKey.INDEX_VALUE).get()).isCloseTo(FORWARD_RATE, offset(TOLERANCE));
    assertThat(explainObs.get(ExplainKey.FROM_FIXING_SERIES)).isNotPresent();
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isCloseTo(DISCOUNT_FACTOR, offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.FIXED_RATE).get()).isCloseTo(fraExp.getFixedRate(), offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.PAY_OFF_RATE).get()).isCloseTo(FORWARD_RATE, offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.COMBINED_RATE).get()).isCloseTo(FORWARD_RATE, offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.UNIT_AMOUNT).get()).isCloseTo(fvExpected.getAmount() / fraExp.getNotional(), offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(fvExpected.getAmount(), offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(pvExpected.getAmount(), offset(TOLERANCE));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.explainPresentValue(RFRA_TRADE, prov)).isEqualTo(test.explainPresentValue(RFRA, prov));
  }
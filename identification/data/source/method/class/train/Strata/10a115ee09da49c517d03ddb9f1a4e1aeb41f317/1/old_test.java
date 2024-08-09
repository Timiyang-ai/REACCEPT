  @Test
  public void test_explainPresentValue_provider() {
    CurrencyAmount fvExpected = PRICER.forecastValue(PAYMENT, PROVIDER);
    CurrencyAmount pvExpected = PRICER.presentValue(PAYMENT, PROVIDER);

    ExplainMap explain = PRICER.explainPresentValue(PAYMENT, PROVIDER);
    Currency currency = PAYMENT.getCurrency();
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("Payment");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(PAYMENT.getDate());
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isCloseTo(DF, offset(TOL));
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(fvExpected.getAmount(), offset(TOL));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(pvExpected.getAmount(), offset(TOL));
  }
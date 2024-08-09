  @Test
  public void test_explainPresentValue() {
    SimpleRatesProvider prov = createProvider(FX_RESET_NOTIONAL_EXCHANGE_REC_USD);

    DiscountingFxResetNotionalExchangePricer test = new DiscountingFxResetNotionalExchangePricer();
    ExplainMapBuilder builder = ExplainMap.builder();
    test.explainPresentValue(FX_RESET_NOTIONAL_EXCHANGE_REC_USD, prov, builder);
    ExplainMap explain = builder.build();

    Currency paymentCurrency = FX_RESET_NOTIONAL_EXCHANGE_REC_USD.getCurrency();
    Currency notionalCurrency = FX_RESET_NOTIONAL_EXCHANGE_REC_USD.getReferenceCurrency();
    double notional = FX_RESET_NOTIONAL_EXCHANGE_REC_USD.getNotional();
    double convertedNotional = notional * FX_RATE;
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("FxResetNotionalExchange");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(FX_RESET_NOTIONAL_EXCHANGE_REC_USD.getPaymentDate());
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(paymentCurrency);
    assertThat(explain.get(ExplainKey.TRADE_NOTIONAL).get().getCurrency()).isEqualTo(notionalCurrency);
    assertThat(explain.get(ExplainKey.TRADE_NOTIONAL).get().getAmount()).isCloseTo(notional, offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isCloseTo(DISCOUNT_FACTOR, offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(paymentCurrency);
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(convertedNotional, offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getCurrency()).isEqualTo(paymentCurrency);
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(convertedNotional * DISCOUNT_FACTOR, offset(TOLERANCE));
  }
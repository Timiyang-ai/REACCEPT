  @Test
  public void test_explainPresentValue() {
    SimpleRatesProvider prov = createProvider(NOTIONAL_EXCHANGE_REC_GBP);

    DiscountingNotionalExchangePricer test = DiscountingNotionalExchangePricer.DEFAULT;
    ExplainMapBuilder builder = ExplainMap.builder();
    test.explainPresentValue(NOTIONAL_EXCHANGE_REC_GBP, prov, builder);
    ExplainMap explain = builder.build();

    Currency currency = NOTIONAL_EXCHANGE_REC_GBP.getCurrency();
    CurrencyAmount notional = NOTIONAL_EXCHANGE_REC_GBP.getPaymentAmount();
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("NotionalExchange");
    assertThat(explain.get(ExplainKey.PAYMENT_DATE).get()).isEqualTo(NOTIONAL_EXCHANGE_REC_GBP.getPaymentDate());
    assertThat(explain.get(ExplainKey.PAYMENT_CURRENCY).get()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.TRADE_NOTIONAL).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.TRADE_NOTIONAL).get().getAmount()).isCloseTo(notional.getAmount(), offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.DISCOUNT_FACTOR).get()).isCloseTo(DISCOUNT_FACTOR, offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(notional.getAmount(), offset(TOLERANCE));
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getCurrency()).isEqualTo(currency);
    assertThat(explain.get(ExplainKey.PRESENT_VALUE).get().getAmount()).isCloseTo(notional.getAmount() * DISCOUNT_FACTOR, offset(TOLERANCE));
  }
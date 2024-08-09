  @Test
  public void test_explainPresentValue_singleCurrency() {
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    when(mockPeriod.presentValue(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, MOCK_PROV))
        .thenReturn(1000d);
    when(mockPeriod.presentValue(FIXED_RATE_PAYMENT_PERIOD_PAY_GBP, MOCK_PROV))
        .thenReturn(-500d);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    when(mockEvent.presentValue(NOTIONAL_EXCHANGE_REC_GBP, MOCK_PROV))
        .thenReturn(35d);
    when(mockEvent.presentValue(NOTIONAL_EXCHANGE_PAY_GBP, MOCK_PROV))
        .thenReturn(-30d);

    DiscountingSwapLegPricer pricerLeg = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    DiscountingSwapProductPricer pricerSwap = new DiscountingSwapProductPricer(pricerLeg);
    assertThat(pricerSwap.presentValue(SWAP, MOCK_PROV)).isEqualTo(MultiCurrencyAmount.of(GBP, 505d));

    ExplainMap explain = pricerSwap.explainPresentValue(SWAP, MOCK_PROV);
    assertThat(explain.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("Swap");

    assertThat(explain.get(ExplainKey.LEGS).get()).hasSize(2);
    ExplainMap explainLeg0 = explain.get(ExplainKey.LEGS).get().get(0);
    ResolvedSwapLeg leg0 = SWAP.getLegs().get(0);
    double fv0 = pricerLeg.forecastValue(leg0, MOCK_PROV).getAmount();
    assertThat(explainLeg0.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("Leg");
    assertThat(explainLeg0.get(ExplainKey.ENTRY_INDEX).get().intValue()).isEqualTo(0);
    assertThat(explainLeg0.get(ExplainKey.PAY_RECEIVE).get()).isEqualTo(leg0.getPayReceive());
    assertThat(explainLeg0.get(ExplainKey.LEG_TYPE).get()).isEqualTo(leg0.getType().toString());
    assertThat(explainLeg0.get(ExplainKey.PAYMENT_PERIODS).get()).hasSize(1);
    assertThat(explainLeg0.get(ExplainKey.PAYMENT_EVENTS).get()).hasSize(1);
    assertThat(explainLeg0.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(leg0.getCurrency());
    assertThat(explainLeg0.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(fv0, offset(TOLERANCE_RATE));
    ExplainMap explainLeg1 = explain.get(ExplainKey.LEGS).get().get(1);
    ResolvedSwapLeg leg1 = SWAP.getLegs().get(0);
    double fv1 = pricerLeg.forecastValue(leg1, MOCK_PROV).getAmount();
    assertThat(explainLeg1.get(ExplainKey.ENTRY_TYPE).get()).isEqualTo("Leg");
    assertThat(explainLeg1.get(ExplainKey.ENTRY_INDEX).get().intValue()).isEqualTo(1);
    assertThat(explainLeg1.get(ExplainKey.PAYMENT_PERIODS).get()).hasSize(1);
    assertThat(explainLeg1.get(ExplainKey.PAYMENT_EVENTS).get()).hasSize(1);
    assertThat(explainLeg1.get(ExplainKey.FORECAST_VALUE).get().getCurrency()).isEqualTo(leg1.getCurrency());
    assertThat(explainLeg1.get(ExplainKey.FORECAST_VALUE).get().getAmount()).isCloseTo(fv1, offset(TOLERANCE_RATE));

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = new DiscountingSwapTradePricer(pricerSwap);
    assertThat(pricerTrade.explainPresentValue(SWAP_TRADE, MOCK_PROV)).isEqualTo(pricerSwap.explainPresentValue(SWAP, MOCK_PROV));
  }
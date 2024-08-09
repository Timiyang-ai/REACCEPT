  @Test
  public void test_forecastValueSensitivity() {
    // ibor leg
    PointSensitivityBuilder sensiFloating = IborRateSensitivity.of(IBOR_RATE_COMP.getObservation(), GBP, 140.0);
    // fixed leg
    PointSensitivityBuilder sensiFixed = PointSensitivityBuilder.none();
    // events
    PointSensitivityBuilder sensiEvent = PointSensitivityBuilder.none();
    PointSensitivities expected = sensiFloating.build();

    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    when(mockPeriod.forecastValueSensitivity(IBOR_SWAP_LEG_REC_GBP.getPaymentPeriods().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiFloating.build().toMutable());
    when(mockPeriod.forecastValueSensitivity(FIXED_SWAP_LEG_PAY.getPaymentPeriods().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiFixed.build().toMutable());
    when(mockEvent.forecastValueSensitivity(IBOR_SWAP_LEG_REC_GBP.getPaymentEvents().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiEvent.build().toMutable());
    when(mockEvent.forecastValueSensitivity(FIXED_SWAP_LEG_PAY.getPaymentEvents().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiEvent.build().toMutable());
    DiscountingSwapLegPricer pricerLeg = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    DiscountingSwapProductPricer pricerSwap = new DiscountingSwapProductPricer(pricerLeg);
    PointSensitivities res = pricerSwap.forecastValueSensitivity(SWAP, MOCK_PROV).build();

    assertThat(res.equalWithTolerance(expected, TOLERANCE_RATE)).isTrue();

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = new DiscountingSwapTradePricer(pricerSwap);
    assertThat(pricerTrade.forecastValueSensitivity(SWAP_TRADE, MOCK_PROV)).isEqualTo(pricerSwap.forecastValueSensitivity(SWAP, MOCK_PROV).build());
  }
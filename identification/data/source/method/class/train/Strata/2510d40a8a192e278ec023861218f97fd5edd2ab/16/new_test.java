  @Test
  public void test_presentValueSensitivity() {
    // ibor leg
    IborRateSensitivity fwdSense = IborRateSensitivity.of(IBOR_RATE_COMP.getObservation(), GBP, 140.0);
    ZeroRateSensitivity dscSense = ZeroRateSensitivity.of(GBP, 3d, -162.0);
    PointSensitivityBuilder sensiFloating = fwdSense.combinedWith(dscSense);
    // fixed leg
    PointSensitivityBuilder sensiFixed = ZeroRateSensitivity.of(GBP, 3d, 152.0);
    // events
    Currency ccy = IBOR_SWAP_LEG_REC_GBP.getCurrency();
    PointSensitivityBuilder sensiEvent = ZeroRateSensitivity.of(ccy, 4d, -134.0);
    PointSensitivities expected = sensiFloating.build()
        .combinedWith(sensiEvent.build())
        .combinedWith(sensiFixed.build())
        .combinedWith(sensiEvent.build());

    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    when(mockPeriod.presentValueSensitivity(IBOR_SWAP_LEG_REC_GBP.getPaymentPeriods().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiFloating.build().toMutable());
    when(mockPeriod.presentValueSensitivity(FIXED_SWAP_LEG_PAY.getPaymentPeriods().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiFixed.build().toMutable());
    when(mockEvent.presentValueSensitivity(IBOR_SWAP_LEG_REC_GBP.getPaymentEvents().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiEvent.build().toMutable());
    when(mockEvent.presentValueSensitivity(FIXED_SWAP_LEG_PAY.getPaymentEvents().get(0), MOCK_PROV))
        .thenAnswer(t -> sensiEvent.build().toMutable());
    DiscountingSwapLegPricer pricerLeg = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    DiscountingSwapProductPricer pricerSwap = new DiscountingSwapProductPricer(pricerLeg);
    PointSensitivities res = pricerSwap.presentValueSensitivity(SWAP, MOCK_PROV).build();

    assertThat(res.equalWithTolerance(expected, TOLERANCE_RATE)).isTrue();

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = new DiscountingSwapTradePricer(pricerSwap);
    assertThat(pricerTrade.presentValueSensitivity(SWAP_TRADE, MOCK_PROV)).isEqualTo(pricerSwap.presentValueSensitivity(SWAP, MOCK_PROV).build());
  }
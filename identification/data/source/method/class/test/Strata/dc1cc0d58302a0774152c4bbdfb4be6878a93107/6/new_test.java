  @Test
  public void test_presentValueSensitivity() {
    ResolvedSwapLeg expSwapLeg = IBOR_SWAP_LEG_REC_GBP;
    Currency ccy = GBP_LIBOR_3M.getCurrency();

    IborRateSensitivity fwdSense = IborRateSensitivity.of(IBOR_RATE_COMP.getObservation(), 140.0);
    ZeroRateSensitivity dscSense = ZeroRateSensitivity.of(ccy, 3d, -162.0);
    PointSensitivityBuilder sensiPeriod = fwdSense.combinedWith(dscSense);
    PointSensitivityBuilder sensiEvent = ZeroRateSensitivity.of(ccy, 4d, -134.0);
    PointSensitivities expected = sensiPeriod.build().combinedWith(sensiEvent.build());

    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    when(mockPeriod.presentValueSensitivity(expSwapLeg.getPaymentPeriods().get(0), MOCK_PROV))
        .thenReturn(sensiPeriod);
    when(mockEvent.presentValueSensitivity(expSwapLeg.getPaymentEvents().get(0), MOCK_PROV))
        .thenReturn(sensiEvent);
    DiscountingSwapLegPricer test = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    PointSensitivities res = test.presentValueSensitivity(expSwapLeg, MOCK_PROV).build();

    assertThat(res.equalWithTolerance(expected, TOLERANCE)).isTrue();
  }
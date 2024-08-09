  @Test
  public void test_forecastValueSensitivity() {
    ResolvedSwapLeg expSwapLeg = IBOR_SWAP_LEG_REC_GBP;
    PointSensitivityBuilder sensiPeriod = IborRateSensitivity.of(IBOR_RATE_COMP.getObservation(), 140.0);
    PointSensitivities expected = sensiPeriod.build();

    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    when(mockPeriod.forecastValueSensitivity(expSwapLeg.getPaymentPeriods().get(0), MOCK_PROV))
        .thenReturn(sensiPeriod);
    when(mockEvent.forecastValueSensitivity(expSwapLeg.getPaymentEvents().get(0), MOCK_PROV))
        .thenReturn(PointSensitivityBuilder.none());
    DiscountingSwapLegPricer test = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    PointSensitivities res = test.forecastValueSensitivity(expSwapLeg, MOCK_PROV).build();

    assertThat(res.equalWithTolerance(expected, TOLERANCE)).isTrue();
  }
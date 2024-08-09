  @Test
  public void test_couponEquivalent_twoPeriods() {
    ResolvedSwapLeg leg = ResolvedSwapLeg.builder()
        .type(FIXED)
        .payReceive(PAY)
        .paymentPeriods(FIXED_RATE_PAYMENT_PERIOD_PAY_USD, FIXED_RATE_PAYMENT_PERIOD_PAY_USD_2)
        .build();
    RatesProvider mockProv = mock(RatesProvider.class);
    double df1 = 0.99d;
    when(mockProv.discountFactor(USD, FIXED_RATE_PAYMENT_PERIOD_PAY_USD.getPaymentDate()))
        .thenReturn(df1);
    double df2 = 0.98d;
    when(mockProv.discountFactor(USD, FIXED_RATE_PAYMENT_PERIOD_PAY_USD_2.getPaymentDate()))
        .thenReturn(df2);
    when(mockProv.getValuationDate()).thenReturn(RatesProviderDataSets.VAL_DATE_2014_01_22);
    double pvbp = PRICER_LEG.pvbp(leg, mockProv);
    double ceExpected = PRICER_LEG.presentValuePeriodsInternal(leg, mockProv) / pvbp;
    double ceComputed = PRICER_LEG.couponEquivalent(leg, mockProv, pvbp);
    assertThat(ceComputed).isCloseTo(ceExpected, offset(TOLERANCE));
  }
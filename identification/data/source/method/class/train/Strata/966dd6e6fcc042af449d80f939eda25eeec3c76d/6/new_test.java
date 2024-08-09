  @Test
  public void test_parRate_singleCurrency() {
    RatesProvider mockProv = mock(RatesProvider.class);
    when(mockProv.discountFactor(GBP, FIXED_RATE_PAYMENT_PERIOD_PAY_GBP.getPaymentDate()))
        .thenReturn(0.99d);
    when(mockProv.getValuationDate()).thenReturn(RatesProviderDataSets.VAL_DATE_2014_01_22);
    when(mockProv.fxRate(GBP, GBP)).thenReturn(1.0);
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    double fwdRate = 0.01;
    double pvCpnIbor = 0.99 * fwdRate * 0.25 * 1_000_000;
    when(mockPeriod.presentValue(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, mockProv))
        .thenReturn(pvCpnIbor);
    double pvbpCpnFixed = -0.99 * 0.25 * 1_000_000;
    double pvCpnFixed = 0.0123d * pvbpCpnFixed;
    when(mockPeriod.presentValue(FIXED_RATE_PAYMENT_PERIOD_PAY_GBP, mockProv))
        .thenReturn(pvCpnFixed);
    when(mockPeriod.pvbp(FIXED_RATE_PAYMENT_PERIOD_PAY_GBP, mockProv))
        .thenReturn(pvbpCpnFixed);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    double pvNotional = 980_000d;
    when(mockEvent.presentValue(NOTIONAL_EXCHANGE_REC_GBP, mockProv))
        .thenReturn(pvNotional);
    when(mockEvent.presentValue(NOTIONAL_EXCHANGE_PAY_GBP, mockProv))
        .thenReturn(-pvNotional);
    DiscountingSwapLegPricer pricerLeg = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    DiscountingSwapProductPricer pricerSwap = new DiscountingSwapProductPricer(pricerLeg);
    double pvbp = pricerLeg.pvbp(FIXED_SWAP_LEG_PAY, mockProv);
    double parRateExpected1 = -(pvCpnIbor + -pvNotional + pvNotional) / pvbp;
    double parRateExpected2 = fwdRate;
    double parRateComputed = pricerSwap.parRate(SWAP, mockProv);
    assertThat(parRateComputed).isCloseTo(parRateExpected1, offset(TOLERANCE_RATE));
    assertThat(parRateComputed).isCloseTo(parRateExpected2, offset(TOLERANCE_RATE));

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = new DiscountingSwapTradePricer(pricerSwap);
    assertThat(pricerTrade.parRate(SWAP_TRADE, MOCK_PROV)).isEqualTo(pricerSwap.parRate(SWAP, MOCK_PROV));
  }
  @Test
  public void test_forecastValue_singleCurrency() {
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    when(mockPeriod.forecastValue(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, MOCK_PROV))
        .thenReturn(1000d);
    when(mockPeriod.forecastValue(FIXED_RATE_PAYMENT_PERIOD_PAY_GBP, MOCK_PROV))
        .thenReturn(-500d);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    DiscountingSwapLegPricer pricerLeg = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    DiscountingSwapProductPricer pricerSwap = new DiscountingSwapProductPricer(pricerLeg);
    assertThat(pricerSwap.forecastValue(SWAP, MOCK_PROV)).isEqualTo(MultiCurrencyAmount.of(GBP, 500d));

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = new DiscountingSwapTradePricer(pricerSwap);
    assertThat(pricerTrade.forecastValue(SWAP_TRADE, MOCK_PROV)).isEqualTo(pricerSwap.forecastValue(SWAP, MOCK_PROV));
  }
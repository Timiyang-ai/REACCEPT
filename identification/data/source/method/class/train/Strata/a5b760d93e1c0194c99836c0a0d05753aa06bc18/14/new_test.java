  @Test
  public void test_accruedInterest_firstAccrualPeriod() {
    RatesProvider prov = new MockRatesProvider(IBOR_RATE_PAYMENT_PERIOD_REC_GBP.getStartDate().plusDays(7));
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    when(mockPeriod.accruedInterest(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, prov))
        .thenReturn(1000d);
    when(mockPeriod.accruedInterest(FIXED_RATE_PAYMENT_PERIOD_PAY_GBP, prov))
        .thenReturn(-500d);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    DiscountingSwapLegPricer pricerLeg = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    DiscountingSwapProductPricer pricerSwap = new DiscountingSwapProductPricer(pricerLeg);
    assertThat(pricerSwap.accruedInterest(SWAP, prov)).isEqualTo(MultiCurrencyAmount.of(GBP, 500d));

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = new DiscountingSwapTradePricer(pricerSwap);
    assertThat(pricerTrade.accruedInterest(SWAP_TRADE, MOCK_PROV)).isEqualTo(pricerSwap.accruedInterest(SWAP, MOCK_PROV));
  }
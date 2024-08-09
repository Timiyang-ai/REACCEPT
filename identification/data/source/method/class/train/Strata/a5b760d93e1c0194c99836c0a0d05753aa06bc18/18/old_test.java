  @Test
  public void test_accruedInterest_firstAccrualPeriod() {
    RatesProvider prov = new MockRatesProvider(IBOR_RATE_PAYMENT_PERIOD_REC_GBP.getStartDate().plusDays(7));
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    when(mockPeriod.accruedInterest(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, prov))
        .thenReturn(1000d);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    DiscountingSwapLegPricer test = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    CurrencyAmount expected = CurrencyAmount.of(GBP, 1000d);
    assertThat(test.accruedInterest(IBOR_SWAP_LEG_REC_GBP, prov)).isEqualTo(expected);
  }
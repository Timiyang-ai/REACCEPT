  @Test
  public void test_presentValue_withCurrency() {
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    when(mockPeriod.presentValue(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, MOCK_PROV))
        .thenReturn(1000d);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    when(mockEvent.presentValue(NOTIONAL_EXCHANGE_REC_GBP, MOCK_PROV))
        .thenReturn(1000d);
    DiscountingSwapLegPricer test = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    CurrencyAmount expected = CurrencyAmount.of(USD, 2000d * 1.6d);
    assertThat(test.presentValue(IBOR_SWAP_LEG_REC_GBP, USD, MOCK_PROV)).isEqualTo(expected);
  }
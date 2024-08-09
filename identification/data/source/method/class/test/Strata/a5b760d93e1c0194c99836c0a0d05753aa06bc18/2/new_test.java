  @Test
  public void test_forecastValue() {
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    when(mockPeriod.forecastValue(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, MOCK_PROV))
        .thenReturn(1000d);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    when(mockEvent.forecastValue(NOTIONAL_EXCHANGE_REC_GBP, MOCK_PROV))
        .thenReturn(1000d);
    DiscountingSwapLegPricer test = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    CurrencyAmount expected = CurrencyAmount.of(GBP, 2000d);
    assertThat(test.forecastValue(IBOR_SWAP_LEG_REC_GBP, MOCK_PROV)).isEqualTo(expected);
  }
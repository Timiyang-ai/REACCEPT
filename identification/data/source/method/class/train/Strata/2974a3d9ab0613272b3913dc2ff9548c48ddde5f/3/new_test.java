  @Test
  public void test_cashFlows() {
    RatesProvider mockProv = mock(RatesProvider.class);
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    double df1 = 0.98;
    double df2 = 0.93;
    double fvGBP = 1000d;
    double fvUSD = -500d;
    when(mockPeriod.forecastValue(IBOR_RATE_PAYMENT_PERIOD_REC_GBP, mockProv)).thenReturn(fvGBP);
    when(mockPeriod.forecastValue(FIXED_RATE_PAYMENT_PERIOD_PAY_USD, mockProv)).thenReturn(fvUSD);
    when(mockProv.getValuationDate()).thenReturn(LocalDate.of(2014, 7, 1));
    when(mockProv.discountFactor(IBOR_RATE_PAYMENT_PERIOD_REC_GBP.getCurrency(),
        IBOR_RATE_PAYMENT_PERIOD_REC_GBP.getPaymentDate())).thenReturn(df1);
    when(mockProv.discountFactor(FIXED_RATE_PAYMENT_PERIOD_PAY_USD.getCurrency(),
        FIXED_RATE_PAYMENT_PERIOD_PAY_USD.getPaymentDate())).thenReturn(df2);
    SwapPaymentEventPricer<SwapPaymentEvent> mockEvent = mock(SwapPaymentEventPricer.class);
    DiscountingSwapLegPricer pricerLeg = new DiscountingSwapLegPricer(mockPeriod, mockEvent);
    DiscountingSwapProductPricer pricerSwap = new DiscountingSwapProductPricer(pricerLeg);

    CashFlows computed = pricerSwap.cashFlows(SWAP_CROSS_CURRENCY, mockProv);
    CashFlow flowGBP = CashFlow.ofForecastValue(IBOR_RATE_PAYMENT_PERIOD_REC_GBP.getPaymentDate(), GBP, fvGBP, df1);
    CashFlow flowUSD = CashFlow.ofForecastValue(FIXED_RATE_PAYMENT_PERIOD_PAY_USD.getPaymentDate(), USD, fvUSD, df2);
    CashFlows expected = CashFlows.of(ImmutableList.of(flowGBP, flowUSD));
    assertThat(computed).isEqualTo(expected);

    // test via SwapTrade
    DiscountingSwapTradePricer pricerTrade = new DiscountingSwapTradePricer(pricerSwap);
    assertThat(pricerTrade.cashFlows(SWAP_TRADE, MOCK_PROV)).isEqualTo(pricerSwap.cashFlows(SWAP, MOCK_PROV));
  }
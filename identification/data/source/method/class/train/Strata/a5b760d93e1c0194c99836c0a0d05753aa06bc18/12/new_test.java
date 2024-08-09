  @Test
  public void test_cashFlows() {
    RatesProvider mockProv = mock(RatesProvider.class);
    SwapPaymentPeriodPricer<SwapPaymentPeriod> mockPeriod = mock(SwapPaymentPeriodPricer.class);
    DispatchingSwapPaymentEventPricer eventPricer = DispatchingSwapPaymentEventPricer.DEFAULT;
    ResolvedSwapLeg expSwapLeg = IBOR_SWAP_LEG_REC_GBP_MULTI;
    SwapPaymentPeriod period1 = IBOR_RATE_PAYMENT_PERIOD_REC_GBP;
    SwapPaymentPeriod period2 = IBOR_RATE_PAYMENT_PERIOD_REC_GBP_2;
    NotionalExchange event = NOTIONAL_EXCHANGE_REC_GBP;
    double fv1 = 520d;
    double fv2 = 450d;
    double df = 1.0d;
    double df1 = 0.98;
    double df2 = 0.93;
    when(mockPeriod.forecastValue(period1, mockProv)).thenReturn(fv1);
    when(mockPeriod.forecastValue(period2, mockProv)).thenReturn(fv2);
    when(mockProv.getValuationDate()).thenReturn(LocalDate.of(2014, 7, 1));
    when(mockProv.discountFactor(expSwapLeg.getCurrency(), period1.getPaymentDate())).thenReturn(df1);
    when(mockProv.discountFactor(expSwapLeg.getCurrency(), period2.getPaymentDate())).thenReturn(df2);
    when(mockProv.discountFactor(expSwapLeg.getCurrency(), event.getPaymentDate())).thenReturn(df);
    DiscountingSwapLegPricer pricer = new DiscountingSwapLegPricer(mockPeriod, eventPricer);

    CashFlows computed = pricer.cashFlows(expSwapLeg, mockProv);
    CashFlow flow1 = CashFlow.ofForecastValue(period1.getPaymentDate(), GBP, fv1, df1);
    CashFlow flow2 = CashFlow.ofForecastValue(period2.getPaymentDate(), GBP, fv2, df2);
    CashFlow flow3 = CashFlow.ofForecastValue(event.getPaymentDate(), GBP, event.getPaymentAmount().getAmount(), df);
    CashFlows expected = CashFlows.of(ImmutableList.of(flow1, flow2, flow3));
    assertThat(computed).isEqualTo(expected);
  }
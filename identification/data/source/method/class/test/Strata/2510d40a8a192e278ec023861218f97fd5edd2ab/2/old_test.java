  @Test
  public void test_pvbp_onePeriod() {
    RatesProvider mockProv = mock(RatesProvider.class);
    double df = 0.99d;
    when(mockProv.discountFactor(USD, FIXED_RATE_PAYMENT_PERIOD_PAY_USD.getPaymentDate()))
        .thenReturn(df);
    double expected = df * FIXED_RATE_PAYMENT_PERIOD_PAY_USD.getNotional() *
        FIXED_RATE_PAYMENT_PERIOD_PAY_USD.getAccrualPeriods().get(0).getYearFraction();
    DiscountingSwapLegPricer test = DiscountingSwapLegPricer.DEFAULT;
    assertThat(test.pvbp(FIXED_SWAP_LEG_PAY_USD, mockProv)).isCloseTo(expected, offset(TOLERANCE));
  }
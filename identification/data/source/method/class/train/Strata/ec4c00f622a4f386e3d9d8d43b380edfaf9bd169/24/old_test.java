  @Test
  public void test_toTrade_tenor() {
    IborIborSwapConvention base = ImmutableIborIborSwapConvention.of(NAME, IBOR3M, IBOR6M);
    LocalDate tradeDate = LocalDate.of(2015, 5, 5);
    LocalDate startDate = date(2015, 5, 7);
    LocalDate endDate = date(2025, 5, 7);
    SwapTrade test = base.createTrade(tradeDate, TENOR_10Y, BUY, NOTIONAL_2M, 0.25d, REF_DATA);
    Swap expected = Swap.of(
        IBOR3M.toLeg(startDate, endDate, PAY, NOTIONAL_2M, 0.25d),
        IBOR6M.toLeg(startDate, endDate, RECEIVE, NOTIONAL_2M));
    assertThat(test.getInfo().getTradeDate()).isEqualTo(Optional.of(tradeDate));
    assertThat(test.getProduct()).isEqualTo(expected);
  }
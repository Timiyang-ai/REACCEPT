  @Test
  public void test_toTrade_tenor() {
    ImmutableXCcyIborIborSwapConvention base = ImmutableXCcyIborIborSwapConvention.of(NAME, EUR3M, USD3M, PLUS_TWO_DAYS);
    LocalDate tradeDate = LocalDate.of(2015, 5, 5);
    LocalDate startDate = date(2015, 5, 7);
    LocalDate endDate = date(2025, 5, 7);
    SwapTrade test = base.createTrade(tradeDate, TENOR_10Y, BUY, NOTIONAL_2M, NOTIONAL_2M * FX_EUR_USD, 0.25d, REF_DATA);
    Swap expected = Swap.of(
        EUR3M.toLeg(startDate, endDate, PAY, NOTIONAL_2M, 0.25d),
        USD3M.toLeg(startDate, endDate, RECEIVE, NOTIONAL_2M * FX_EUR_USD));
    assertThat(test.getInfo().getTradeDate()).isEqualTo(Optional.of(tradeDate));
    assertThat(test.getProduct()).isEqualTo(expected);
  }
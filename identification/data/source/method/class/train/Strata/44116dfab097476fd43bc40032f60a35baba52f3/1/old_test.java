  @Test
  public void test_createTrade() {
    FixedInflationSwapTemplate base = FixedInflationSwapTemplate.of(TENOR_10Y, CONV);
    LocalDate tradeDate = LocalDate.of(2015, 5, 5);
    LocalDate startDate = date(2015, 5, 6); // T+1
    LocalDate endDate = date(2025, 5, 6);
    SwapTrade test = base.createTrade(tradeDate, BUY, NOTIONAL_2M, 0.25d, REF_DATA);
    Swap expected = Swap.of(
        FIXED.toLeg(startDate, endDate, PAY, NOTIONAL_2M, 0.25d),
        INFL.toLeg(startDate, endDate, RECEIVE, NOTIONAL_2M));
    assertThat(test.getInfo().getTradeDate()).isEqualTo(Optional.of(tradeDate));
    assertThat(test.getProduct().getLegs().get(0)).isEqualTo(expected.getLegs().get(0));
    assertThat(test.getProduct().getLegs().get(1)).isEqualTo(expected.getLegs().get(1));
    assertThat(test.getProduct()).isEqualTo(expected);
  }
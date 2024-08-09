  @Test
  public void test_getEndDate() {
    SwapLeg leg1 = MockSwapLeg.of(FIXED, PAY, date(2015, 6, 29), date(2017, 6, 30), Currency.USD);
    SwapLeg leg2 = MockSwapLeg.of(FIXED, RECEIVE, date(2015, 6, 30), date(2017, 6, 29), Currency.USD);
    assertThat(Swap.of(leg1).getEndDate()).isEqualTo(AdjustableDate.of(date(2017, 6, 30)));
    assertThat(Swap.of(leg2).getEndDate()).isEqualTo(AdjustableDate.of(date(2017, 6, 29)));
    assertThat(Swap.of(leg1, leg2).getEndDate()).isEqualTo(AdjustableDate.of(date(2017, 6, 30)));
    assertThat(Swap.of(leg2, leg1).getEndDate()).isEqualTo(AdjustableDate.of(date(2017, 6, 30)));
  }
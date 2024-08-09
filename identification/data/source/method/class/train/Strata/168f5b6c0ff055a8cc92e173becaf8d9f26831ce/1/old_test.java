  @Test
  public void test_annuityCash_onePeriod() {
    double yield = 0.01;
    DiscountingSwapLegPricer test = DiscountingSwapLegPricer.DEFAULT;
    double computed = test.annuityCash(FIXED_SWAP_LEG_REC_USD, yield);
    double expected = SwapDummyData.NOTIONAL * (1d - 1d / (1d + yield / 4d)) / yield;
    assertThat(computed).isCloseTo(expected, offset(SwapDummyData.NOTIONAL * TOLERANCE));
  }
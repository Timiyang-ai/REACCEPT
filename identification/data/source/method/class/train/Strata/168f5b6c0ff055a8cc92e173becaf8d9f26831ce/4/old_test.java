  @Test
  public void test_annuityCashDerivative_onePeriod() {
    double yield = 0.01;
    DiscountingSwapLegPricer test = DiscountingSwapLegPricer.DEFAULT;
    double computed = test.annuityCashDerivative(FIXED_SWAP_LEG_REC_USD, yield).getDerivative(0);
    double expected = 0.5 * (test.annuityCash(FIXED_SWAP_LEG_REC_USD, yield + FD_SHIFT)
        - test.annuityCash(FIXED_SWAP_LEG_REC_USD, yield - FD_SHIFT)) / FD_SHIFT;
    assertThat(computed).isCloseTo(expected, offset(SwapDummyData.NOTIONAL * FD_SHIFT));
  }
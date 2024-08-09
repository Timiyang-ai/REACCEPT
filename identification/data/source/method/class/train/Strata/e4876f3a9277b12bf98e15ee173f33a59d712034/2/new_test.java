  @Test
  public void test_requirements() {
    XCcyIborIborSwapCurveNode test = XCcyIborIborSwapCurveNode.of(TEMPLATE, SPREAD_ID, SPREAD_ADJ);
    Set<? extends MarketDataId<?>> setExpected = ImmutableSet.of(SPREAD_ID, FX_RATE_ID);
    Set<? extends MarketDataId<?>> set = test.requirements();
    assertThat(set.equals(setExpected)).isTrue();
  }
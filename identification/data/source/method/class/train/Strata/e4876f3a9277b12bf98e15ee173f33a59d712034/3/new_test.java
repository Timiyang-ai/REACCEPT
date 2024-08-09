  @Test
  public void test_requirements() {
    FxSwapCurveNode test = FxSwapCurveNode.of(TEMPLATE, QUOTE_ID_PTS);
    Set<? extends MarketDataId<?>> setExpected = ImmutableSet.of(FX_RATE_ID, QUOTE_ID_PTS);
    Set<? extends MarketDataId<?>> set = test.requirements();
    assertThat(set.equals(setExpected)).isTrue();
  }
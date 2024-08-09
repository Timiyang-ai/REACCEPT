  @Test
  public void test_resolve() {
    FxSwapTrade test = sut();
    ResolvedFxSwapTrade expected = ResolvedFxSwapTrade.of(TRADE_INFO, PRODUCT.resolve(REF_DATA));
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }
  @Test
  public void test_resolve() {
    FxNdfTrade test = sut();
    ResolvedFxNdfTrade expected = ResolvedFxNdfTrade.of(TRADE_INFO, PRODUCT.resolve(REF_DATA));
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }
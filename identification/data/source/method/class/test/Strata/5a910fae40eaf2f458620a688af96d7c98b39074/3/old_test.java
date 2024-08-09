  @Test
  public void test_resolve() {
    FxSingleTrade test = FxSingleTrade.builder()
        .product(PRODUCT)
        .info(TRADE_INFO)
        .build();
    ResolvedFxSingleTrade expected = ResolvedFxSingleTrade.of(TRADE_INFO, PRODUCT.resolve(REF_DATA));
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }
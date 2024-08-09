  @Test
  public void test_resolve() {
    IborCapFloorTrade test = sut();
    ResolvedIborCapFloorTrade expected = ResolvedIborCapFloorTrade.builder()
        .info(TRADE_INFO)
        .product(PRODUCT.resolve(REF_DATA))
        .premium(PREMIUM.resolve(REF_DATA))
        .build();
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }
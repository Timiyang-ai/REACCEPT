  @Test
  public void test_resolve() {
    ResolvedCmsTrade expected = ResolvedCmsTrade.builder()
        .info(TRADE_INFO)
        .product(PRODUCT_CAP.resolve(REF_DATA))
        .premium(PREMIUM.resolve(REF_DATA))
        .build();
    assertThat(sut().resolve(REF_DATA)).isEqualTo(expected);
  }
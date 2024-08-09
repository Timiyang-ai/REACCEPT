  @Test
  public void test_resolve() {
    ResolvedCapitalIndexedBondTrade test = sut().resolve(REF_DATA);
    ResolvedCapitalIndexedBondTrade expected = ResolvedCapitalIndexedBondTrade.builder()
        .info(TRADE_INFO)
        .product(PRODUCT.resolve(REF_DATA))
        .quantity(QUANTITY)
        .settlement(ResolvedCapitalIndexedBondSettlement.of(SETTLEMENT_DATE, PRICE, SETTLEMENT))
        .build();
    assertThat(test).isEqualTo(expected);
  }
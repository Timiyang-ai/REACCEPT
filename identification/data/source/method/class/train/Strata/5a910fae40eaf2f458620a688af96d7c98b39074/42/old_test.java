  @Test
  public void test_resolve() {
    ResolvedFixedCouponBondTrade expected = ResolvedFixedCouponBondTrade.builder()
        .info(TRADE_INFO)
        .product(PRODUCT.resolve(REF_DATA))
        .quantity(QUANTITY)
        .settlement(ResolvedFixedCouponBondSettlement.of(SETTLEMENT_DATE, PRICE))
        .build();
    assertThat(sut().resolve(REF_DATA)).isEqualTo(expected);
  }
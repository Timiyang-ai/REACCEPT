  @Test
  public void test_resolve() {
    ResolvedBondFutureOptionTrade expected = ResolvedBondFutureOptionTrade.builder()
        .info(TRADE_INFO)
        .product(OPTION_PRODUCT.resolve(REF_DATA))
        .quantity(QUANTITY)
        .tradedPrice(TradedPrice.of(TRADE_INFO.getTradeDate().get(), PRICE))
        .build();
    assertThat(sut().resolve(REF_DATA)).isEqualTo(expected);
  }
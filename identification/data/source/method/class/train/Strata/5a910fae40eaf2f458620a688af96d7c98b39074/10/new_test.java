  @Test
  public void test_resolve() {
    ResolvedBondFutureTrade expected = ResolvedBondFutureTrade.builder()
        .info(TRADE_INFO)
        .product(FUTURE.resolve(REF_DATA))
        .quantity(QUANTITY)
        .tradedPrice(TradedPrice.of(TRADE_INFO.getTradeDate().get(), PRICE))
        .build();
    assertThat(sut().resolve(REF_DATA)).isEqualTo(expected);
  }
  @Test
  public void test_resolve() {
    IborFutureOptionTrade test = sut();
    ResolvedIborFutureOptionTrade resolved = test.resolve(REF_DATA);
    assertThat(resolved.getInfo()).isEqualTo(TRADE_INFO);
    assertThat(resolved.getProduct()).isEqualTo(PRODUCT.resolve(REF_DATA));
    assertThat(resolved.getQuantity()).isEqualTo(QUANTITY);
    assertThat(resolved.getTradedPrice()).isEqualTo(Optional.of(TradedPrice.of(TRADE_DATE, PRICE)));
  }
  @Test
  public void test_withPrice() {
    BillTrade base = sut_yield();
    double price = 135d;
    BillTrade computed = base.withPrice(price);
    BillTrade expected = BillTrade.builder()
        .info(TRADE_INFO)
        .product(PRODUCT)
        .quantity(QUANTITY)
        .price(price)
        .build();
    assertThat(computed).isEqualTo(expected);
  }
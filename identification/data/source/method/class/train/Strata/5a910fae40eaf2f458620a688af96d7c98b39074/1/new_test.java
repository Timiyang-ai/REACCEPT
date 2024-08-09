  @Test
  public void test_resolve() {
    FraTrade test = FraTrade.of(TRADE_INFO, PRODUCT);
    assertThat(test.resolve(REF_DATA).getInfo()).isEqualTo(TRADE_INFO);
    assertThat(test.resolve(REF_DATA).getProduct()).isEqualTo(PRODUCT.resolve(REF_DATA));
  }
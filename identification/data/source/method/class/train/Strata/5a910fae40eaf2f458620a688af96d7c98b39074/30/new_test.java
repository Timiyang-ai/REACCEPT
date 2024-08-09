  @Test
  public void test_resolve() {
    BulletPaymentTrade test = BulletPaymentTrade.of(TRADE_INFO, PRODUCT1);
    assertThat(test.resolve(REF_DATA).getInfo()).isEqualTo(TRADE_INFO);
    assertThat(test.resolve(REF_DATA).getProduct()).isEqualTo(PRODUCT1.resolve(REF_DATA));
  }
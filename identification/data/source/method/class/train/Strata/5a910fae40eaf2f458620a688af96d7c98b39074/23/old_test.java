  @Test
  public void test_resolve() {
    SwapTrade test = SwapTrade.of(TRADE_INFO, SWAP1);
    assertThat(test.resolve(REF_DATA).getInfo()).isEqualTo(TRADE_INFO);
    assertThat(test.resolve(REF_DATA).getProduct()).isEqualTo(SWAP1.resolve(REF_DATA));
  }
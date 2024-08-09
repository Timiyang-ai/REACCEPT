  @Test
  public void test_resolve() {
    TermDepositTrade test = TermDepositTrade.of(TRADE_INFO, DEPOSIT);
    assertThat(test.resolve(REF_DATA).getInfo()).isEqualTo(TRADE_INFO);
    assertThat(test.resolve(REF_DATA).getProduct()).isEqualTo(DEPOSIT.resolve(REF_DATA));
  }
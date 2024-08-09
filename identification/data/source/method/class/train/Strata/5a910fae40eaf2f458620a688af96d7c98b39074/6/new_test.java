  @Test
  public void test_resolve() {
    SwaptionTrade test = SwaptionTrade.of(TRADE_INFO, SWAPTION, PREMIUM);
    assertThat(test.resolve(REF_DATA).getPremium()).isEqualTo(PREMIUM.resolve(REF_DATA));
    assertThat(test.resolve(REF_DATA).getProduct()).isEqualTo(SWAPTION.resolve(REF_DATA));
    assertThat(test.resolve(REF_DATA).getInfo()).isEqualTo(TRADE_INFO);
  }
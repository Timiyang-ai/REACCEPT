  @Test
  public void test_jumpToDefault() {
    JumpToDefault computed = PRICER.jumpToDefault(TRADE, RATES_PROVIDER, REF_DATA);
    JumpToDefault expected = PRICER_PRODUCT.jumpToDefault(PRODUCT, RATES_PROVIDER, SETTLEMENT_DATE, REF_DATA);
    assertThat(computed).isEqualTo(expected);
  }
  @Test
  public void test_minus_CurrencyAmount() {
    CurrencyAmount ccyAmount = CurrencyAmount.of(CCY1, AMT2);
    CurrencyAmount test = CCY_AMOUNT.minus(ccyAmount);
    assertThat(test).isEqualTo(CurrencyAmount.of(CCY1, AMT1 - AMT2));
  }
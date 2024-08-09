  @Test
  public void test_ofCurrencyMinorUnit_GBP() {
    SecurityPriceInfo test = SecurityPriceInfo.ofCurrencyMinorUnit(GBP);
    assertThat(test.getTickSize()).isEqualTo(0.01);
    assertThat(test.getTickValue()).isEqualTo(CurrencyAmount.of(GBP, 0.01));
    assertThat(test.getContractSize()).isEqualTo(1d);
    assertThat(test.getCurrency()).isEqualTo(GBP);
  }
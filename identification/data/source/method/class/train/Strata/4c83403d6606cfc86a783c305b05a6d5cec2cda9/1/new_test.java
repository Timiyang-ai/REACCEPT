  @Test
  public void test_currentCash_zero() {
    MultiCurrencyAmount computed = PRICER.currentCash(FWD, PROVIDER.getValuationDate());
    assertThat(computed).isEqualTo(MultiCurrencyAmount.empty());
  }
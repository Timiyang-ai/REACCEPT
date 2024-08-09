  @Test
  public void test_currentCash_zero() {
    MultiCurrencyAmount computed = PRICER.currentCash(SWAP_PRODUCT, PROVIDER.getValuationDate());
    assertThat(computed).isEqualTo(MultiCurrencyAmount.empty());
  }
  @Test
  public void test_mapAmounts() {
    MultiCurrencyAmount base = MultiCurrencyAmount.of(CA1, CA2);
    MultiCurrencyAmount test = base.mapAmounts(a -> a * 2.5 + 1);
    assertMCA(test, CA1.mapAmount(a -> a * 2.5 + 1), CA2.mapAmount(a -> a * 2.5 + 1));
  }
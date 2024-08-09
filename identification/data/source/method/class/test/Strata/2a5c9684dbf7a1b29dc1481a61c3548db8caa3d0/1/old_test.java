  @Test
  public void test_total_Iterable() {
    Iterable<CurrencyAmount> iterable = Arrays.asList(CA1, CA3);
    assertMCA(MultiCurrencyAmount.total(iterable), CA1, CA3);
  }
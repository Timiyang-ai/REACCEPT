  @Test
  public void test_collector() {
    List<CurrencyAmount> amount = ImmutableList.of(
        CurrencyAmount.of(CCY1, 100), CurrencyAmount.of(CCY1, 150), CurrencyAmount.of(CCY2, 100));
    MultiCurrencyAmount test = amount.stream().collect(toMultiCurrencyAmount());
    MultiCurrencyAmount expected = MultiCurrencyAmount.of(CurrencyAmount.of(CCY1, 250), CurrencyAmount.of(CCY2, 100));
    assertThat(test).isEqualTo(expected);
  }
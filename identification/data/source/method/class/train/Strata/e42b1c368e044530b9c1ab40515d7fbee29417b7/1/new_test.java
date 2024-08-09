  @Test
  public void test_negated() {
    MultiCurrencyAmount base = MultiCurrencyAmount.of(CA1, CA2);
    MultiCurrencyAmount test = base.negated();
    assertMCA(test, CA1.negated(), CA2.negated());
    assertThat(MultiCurrencyAmount.of(CurrencyAmount.zero(USD), CurrencyAmount.zero(EUR)).negated())
        .isEqualTo(MultiCurrencyAmount.of(CurrencyAmount.zero(USD), CurrencyAmount.zero(EUR)));
    assertThat(MultiCurrencyAmount.of(CurrencyAmount.of(USD, -0d), CurrencyAmount.of(EUR, -0d)).negated())
        .isEqualTo(MultiCurrencyAmount.of(CurrencyAmount.zero(USD), CurrencyAmount.zero(EUR)));
  }
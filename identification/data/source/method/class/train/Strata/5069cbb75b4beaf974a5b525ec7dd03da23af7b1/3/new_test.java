  @Test
  public void stream() {
    List<MultiCurrencyAmount> expected = ImmutableList.of(
        MultiCurrencyAmount.of(
            CurrencyAmount.of(Currency.GBP, 20),
            CurrencyAmount.of(Currency.USD, 30),
            CurrencyAmount.of(Currency.EUR, 40)),
        MultiCurrencyAmount.of(
            CurrencyAmount.of(Currency.GBP, 21),
            CurrencyAmount.of(Currency.USD, 32),
            CurrencyAmount.of(Currency.EUR, 43)),
        MultiCurrencyAmount.of(
            CurrencyAmount.of(Currency.GBP, 22),
            CurrencyAmount.of(Currency.USD, 33),
            CurrencyAmount.of(Currency.EUR, 44)));

    assertThat(VALUES_ARRAY.stream().collect(toList())).isEqualTo(expected);
  }
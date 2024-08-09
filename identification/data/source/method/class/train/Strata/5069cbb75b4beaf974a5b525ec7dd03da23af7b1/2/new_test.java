  @Test
  public void get() {
    MultiCurrencyAmount expected = MultiCurrencyAmount.of(
        CurrencyAmount.of(Currency.GBP, 22),
        CurrencyAmount.of(Currency.USD, 33),
        CurrencyAmount.of(Currency.EUR, 44));
    assertThat(VALUES_ARRAY.get(2)).isEqualTo(expected);
    assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> VALUES_ARRAY.get(3));
    assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> VALUES_ARRAY.get(-1));
  }
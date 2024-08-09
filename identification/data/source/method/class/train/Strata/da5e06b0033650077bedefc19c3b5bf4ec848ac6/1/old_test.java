  @Test
  public void test_minus_currencyAmount() {
    DoubleArray values = DoubleArray.of(1, 2, 3);
    CurrencyAmountArray array = CurrencyAmountArray.of(GBP, values);

    CurrencyAmountArray result = array.minus(CurrencyAmount.of(GBP, 0.5));
    assertThat(result).isEqualTo(CurrencyAmountArray.of(GBP, DoubleArray.of(0.5, 1.5, 2.5)));
  }
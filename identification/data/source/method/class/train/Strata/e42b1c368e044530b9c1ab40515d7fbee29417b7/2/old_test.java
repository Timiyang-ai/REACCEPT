  @Test
  public void test_negated() {
    assertThat(CCY_AMOUNT.negated()).isEqualTo(CCY_AMOUNT_NEGATIVE);
    assertThat(CCY_AMOUNT_NEGATIVE.negated()).isEqualTo(CCY_AMOUNT);
    assertThat(CurrencyAmount.zero(Currency.USD)).isEqualTo(CurrencyAmount.zero(Currency.USD).negated());
    assertThat(CurrencyAmount.of(Currency.USD, -0d).negated()).isEqualTo(CurrencyAmount.zero(Currency.USD));
  }
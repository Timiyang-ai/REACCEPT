  @Test
  public void test_plus_CurrencyDouble_merge() {
    CurrencyAmount ca = CurrencyAmount.of(Currency.AUD, 117);
    CurrencyAmount cb = CurrencyAmount.of(Currency.USD, 12);
    MultiCurrencyAmount mc1 = MultiCurrencyAmount.of(ca, cb);
    MultiCurrencyAmount test = mc1.plus(Currency.AUD, 3);
    assertMCA(test, cb, CurrencyAmount.of(Currency.AUD, 120));
  }
  @Test
  public void test_contains_null() {
    MultiCurrencyAmount base = MultiCurrencyAmount.of(CA1, CA2);
    assertThatIllegalArgumentException().isThrownBy(() -> base.contains(null));
  }
  @Test
  public void test_parseCurrency() {
    assertThat(LoaderUtils.parseCurrency("GBP")).isEqualTo(Currency.GBP);
    assertThatIllegalArgumentException().isThrownBy(() -> LoaderUtils.parseCurrency("A"));
  }
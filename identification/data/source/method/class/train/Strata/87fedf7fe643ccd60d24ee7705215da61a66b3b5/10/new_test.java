  @Test
  public void test_withCurrency() {
    IborRateSensitivity base = IborRateSensitivity.of(GBP_LIBOR_3M_OBSERVATION, 32d);
    assertThat(base.withCurrency(GBP)).isSameAs(base);

    IborRateSensitivity expected = IborRateSensitivity.of(GBP_LIBOR_3M_OBSERVATION, USD, 32d);
    IborRateSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }
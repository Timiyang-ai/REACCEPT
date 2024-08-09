  @Test
  public void test_multipliedBy() {
    IborRateSensitivity base = IborRateSensitivity.of(GBP_LIBOR_3M_OBSERVATION, 32d);
    IborRateSensitivity expected = IborRateSensitivity.of(GBP_LIBOR_3M_OBSERVATION, 32d * 3.5d);
    IborRateSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }
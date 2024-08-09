  @Test
  public void test_multipliedBy() {
    OvernightRateSensitivity base = OvernightRateSensitivity.of(GBP_SONIA_OBSERVATION, 32d);
    OvernightRateSensitivity expected = OvernightRateSensitivity.of(GBP_SONIA_OBSERVATION, 32d * 3.5d);
    OvernightRateSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }
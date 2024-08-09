  @Test
  public void test_withCurrency() {
    OvernightRateSensitivity base = OvernightRateSensitivity.of(GBP_SONIA_OBSERVATION, 32d);
    assertThat(base.withCurrency(GBP)).isSameAs(base);

    LocalDate mat = GBP_SONIA_OBSERVATION.getMaturityDate();
    OvernightRateSensitivity expected = OvernightRateSensitivity.ofPeriod(GBP_SONIA_OBSERVATION, mat, USD, 32d);
    OvernightRateSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }
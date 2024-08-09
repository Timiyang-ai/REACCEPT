  @Test
  public void test_multipliedBy() {
    SwaptionSabrSensitivity base = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR, SabrParameterType.ALPHA, GBP, 32d);
    SwaptionSabrSensitivity expected = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR, SabrParameterType.ALPHA, GBP, 32d * 3.5d);
    SwaptionSabrSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }
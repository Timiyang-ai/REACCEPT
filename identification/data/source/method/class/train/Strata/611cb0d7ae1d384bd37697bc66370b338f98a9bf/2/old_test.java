  @Test
  public void test_multipliedBy() {
    SwaptionSensitivity base = SwaptionSensitivity.of(NAME, EXPIRY, TENOR, STRIKE, FORWARD, GBP, 32d);
    SwaptionSensitivity expected = SwaptionSensitivity.of(NAME, EXPIRY, TENOR, STRIKE, FORWARD, GBP, 32d * 3.5d);
    SwaptionSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }
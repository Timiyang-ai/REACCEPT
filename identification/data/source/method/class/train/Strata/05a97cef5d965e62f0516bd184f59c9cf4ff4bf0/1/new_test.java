  @Test
  public void test_multipliedBy() {
    ZeroRateSensitivity base = ZeroRateSensitivity.of(GBP, YEARFRAC, 32d);
    ZeroRateSensitivity expected = ZeroRateSensitivity.of(GBP, YEARFRAC, 32d * 3.5d);
    ZeroRateSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }
  @Test
  public void test_multipliedBy() {
    IborCapletFloorletSensitivity base =
        IborCapletFloorletSensitivity.of(NAME, EXPIRY, STRIKE, FORWARD, GBP, SENSITIVITY);
    double factor = 3.5d;
    IborCapletFloorletSensitivity expected =
        IborCapletFloorletSensitivity.of(NAME, EXPIRY, STRIKE, FORWARD, GBP, SENSITIVITY * factor);
    IborCapletFloorletSensitivity test = base.multipliedBy(3.5d);
    assertThat(test).isEqualTo(expected);
  }
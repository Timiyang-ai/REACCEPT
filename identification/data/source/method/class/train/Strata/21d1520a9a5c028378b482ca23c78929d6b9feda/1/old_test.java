  @Test
  public void test_withCurrency() {
    IborCapletFloorletSensitivity base =
        IborCapletFloorletSensitivity.of(NAME, EXPIRY, STRIKE, FORWARD, GBP, SENSITIVITY);
    IborCapletFloorletSensitivity expected =
        IborCapletFloorletSensitivity.of(NAME, EXPIRY, STRIKE, FORWARD, USD, SENSITIVITY);
    IborCapletFloorletSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }
  @Test
  public void test_withCurrency() {
    SwaptionSabrSensitivity base = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR, SabrParameterType.ALPHA, GBP, 32d);
    assertThat(base.withCurrency(GBP)).isSameAs(base);

    SwaptionSabrSensitivity expected = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR, SabrParameterType.ALPHA, USD, 32d);
    SwaptionSabrSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }
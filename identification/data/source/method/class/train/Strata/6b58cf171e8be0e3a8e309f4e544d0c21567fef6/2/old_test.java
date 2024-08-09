  @Test
  public void test_withCurrency() {
    SwaptionSensitivity base = SwaptionSensitivity.of(NAME, EXPIRY, TENOR, STRIKE, FORWARD, GBP, 32d);
    assertThat(base.withCurrency(GBP)).isSameAs(base);

    SwaptionSensitivity expected = SwaptionSensitivity.of(NAME, EXPIRY, TENOR, STRIKE, FORWARD, USD, 32d);
    SwaptionSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }
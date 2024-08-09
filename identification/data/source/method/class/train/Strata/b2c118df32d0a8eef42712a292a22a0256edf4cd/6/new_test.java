  @Test
  public void test_withCurrency() {
    FxOptionSensitivity base = FxOptionSensitivity.of(NAME, PAIR, EXPIRY, STRIKE, FORWARD, GBP, SENSI_VALUE);
    FxOptionSensitivity test1 = base.withCurrency(EUR);
    assertThat(test1.getCurrency()).isEqualTo(EUR);
    assertThat(test1.getExpiry()).isEqualTo(EXPIRY);
    assertThat(test1.getForward()).isEqualTo(FORWARD);
    assertThat(test1.getCurrencyPair()).isEqualTo(PAIR);
    assertThat(test1.getSensitivity()).isEqualTo(SENSI_VALUE);
    assertThat(test1.getStrike()).isEqualTo(STRIKE);
    FxOptionSensitivity test2 = base.withCurrency(GBP);
    assertThat(test2).isEqualTo(base);
  }
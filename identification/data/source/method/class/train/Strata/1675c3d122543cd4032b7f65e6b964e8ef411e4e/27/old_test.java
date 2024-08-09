  @Test
  public void test_presentValueFromCleanPrice_standard() {
    CurrencyAmount computed = PRICER.presentValueFromCleanPrice(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE);
    assertThat(computed.getAmount()).isCloseTo(0d, offset(NOTIONAL * QUANTITY * TOL));
  }
  @Test
  public void test_presentValue() {
    CurrencyAmount computed =
        OPTION_TRADE_PRICER.presentValue(OPTION_TRADE, RATE_PROVIDER, VOLS, REFERENCE_PRICE);
    double expected = (OPTION_PRODUCT_PRICER.price(OPTION_PRODUCT, RATE_PROVIDER, VOLS) - REFERENCE_PRICE)
        * NOTIONAL * QUANTITY;
    assertThat(computed.getCurrency()).isEqualTo(Currency.EUR);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(TOL * NOTIONAL * QUANTITY));
  }
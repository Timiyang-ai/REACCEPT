  @Test
  public void test_presentValue_notStarted() {
    SimpleRatesProvider prov = provider(VAL_DATE, DF_START, DF_END);
    CurrencyAmount computed = PRICER.presentValue(RTERM_DEPOSIT, prov);
    double expected = ((1d + RATE * RTERM_DEPOSIT.getYearFraction()) * DF_END - DF_START) * NOTIONAL;
    assertThat(computed.getCurrency()).isEqualTo(EUR);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(TOLERANCE * NOTIONAL));
  }
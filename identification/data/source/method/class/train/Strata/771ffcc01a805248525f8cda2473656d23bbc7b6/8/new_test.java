  @Test
  public void test_presentValue_noFixing() {
    double discountFactor = IMM_PROV_NOFIX.discountFactor(EUR, END_DATE);
    double forwardRate = IMM_PROV_NOFIX.iborIndexRates(EUR_EURIBOR_6M).rate(RDEPOSIT.getFloatingRate().getObservation());
    CurrencyAmount computed = PRICER.presentValue(RDEPOSIT, IMM_PROV_NOFIX);
    double expected = NOTIONAL * discountFactor * (RATE - forwardRate) * RDEPOSIT.getYearFraction();
    assertThat(computed.getCurrency()).isEqualTo(EUR);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(TOLERANCE_PV));
  }
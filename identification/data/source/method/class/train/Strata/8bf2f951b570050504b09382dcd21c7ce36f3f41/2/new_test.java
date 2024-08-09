  @Test
  public void price() {
    double strikeIn = 0.08;
    double strikeAt = CUT_OFF_STRIKE;
    double strikeOut = 0.12;
    double volatilityIn = SABR_FUNCTION.volatility(FORWARD, strikeIn, TIME_TO_EXPIRY, SABR_DATA);
    double priceExpectedIn = BlackFormulaRepository.price(FORWARD, strikeIn, TIME_TO_EXPIRY, volatilityIn, true);
    double priceIn = SABR_EXTRAPOLATION.price(strikeIn, PutCall.CALL);
    assertThat(priceExpectedIn).isCloseTo(priceIn, offset(TOLERANCE_PRICE));
    double volatilityAt = SABR_FUNCTION.volatility(FORWARD, strikeAt, TIME_TO_EXPIRY, SABR_DATA);
    double priceExpectedAt = BlackFormulaRepository.price(FORWARD, strikeAt, TIME_TO_EXPIRY, volatilityAt, true);
    double priceAt = SABR_EXTRAPOLATION.price(strikeAt, PutCall.CALL);
    assertThat(priceExpectedAt).isCloseTo(priceAt, offset(TOLERANCE_PRICE));
    double priceOut = SABR_EXTRAPOLATION.price(strikeOut, PutCall.CALL);
    double priceExpectedOut = 5.427104E-5; // From previous run
    assertThat(priceExpectedOut).isCloseTo(priceOut, offset(TOLERANCE_PRICE));
  }
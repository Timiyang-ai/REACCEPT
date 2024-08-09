  @Test
  public void test_price() {
    double computed = OPTION_PRICER.price(FUTURE_OPTION_PRODUCT, RATE_PROVIDER, VOLS);
    double futurePrice = FUTURE_PRICER.price(FUTURE_OPTION_PRODUCT.getUnderlyingFuture(), RATE_PROVIDER);
    double strike = FUTURE_OPTION_PRODUCT.getStrikePrice();
    double expiryTime = ACT_365F.relativeYearFraction(VAL_DATE, FUTURE_OPTION_PRODUCT.getExpiryDate());
    double logMoneyness = Math.log(strike / futurePrice);
    double vol = SURFACE.zValue(expiryTime, logMoneyness);
    double expected = BlackFormulaRepository.price(futurePrice, strike, expiryTime, vol, true);
    assertThat(computed).isCloseTo(expected, offset(TOL));
  }
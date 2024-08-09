  @Test
  public void test_price_presentValue() {
    for (int i = 0; i < NB_STRIKES; ++i) {
      ResolvedFxVanillaOption call = CALLS[i];
      ResolvedFxVanillaOptionTrade callTrade = ResolvedFxVanillaOptionTrade.builder()
          .product(call)
          .premium(Payment.of(EUR, 0, VAL_DATE))
          .build();
      double computedPriceCall = PRICER.price(call, RATES_PROVIDER, VOLS);
      CurrencyAmount computedCall = PRICER.presentValue(call, RATES_PROVIDER, VOLS);
      double timeToExpiry = VOLS.relativeTime(EXPIRY);
      FxRate forward = FX_PRICER.forwardFxRate(UNDERLYING[i], RATES_PROVIDER);
      double forwardRate = forward.fxRate(CURRENCY_PAIR);
      double strikeRate = call.getStrike();
      SmileDeltaParameters smileAtTime = VOLS.getSmile().smileForExpiry(timeToExpiry);
      double[] strikes = smileAtTime.strike(forwardRate).toArray();
      double[] vols = smileAtTime.getVolatility().toArray();
      double df = RATES_PROVIDER.discountFactor(USD, PAY);
      double[] weights = weights(forwardRate, strikeRate, strikes, timeToExpiry, vols[1]);
      double expectedPriceCall = BlackFormulaRepository.price(forwardRate, strikeRate, timeToExpiry, vols[1], true);
      for (int j = 0; j < 3; ++j) {
        expectedPriceCall += weights[j] * (
            BlackFormulaRepository.price(forwardRate, strikes[j], timeToExpiry, vols[j], true)
            - BlackFormulaRepository.price(forwardRate, strikes[j], timeToExpiry, vols[1], true));
      }
      expectedPriceCall *= df;
      assertThat(computedPriceCall).isCloseTo(expectedPriceCall, offset(TOL));
      assertThat(computedCall.getAmount()).isCloseTo(expectedPriceCall * NOTIONAL, offset(TOL * NOTIONAL));
      // test against trade pricer
      assertThat(computedCall).isEqualTo(TRADE_PRICER.presentValue(callTrade, RATES_PROVIDER, VOLS).getAmount(USD));
    }
  }
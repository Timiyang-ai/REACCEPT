  @Test
  public void test_presentValueSensitivityVolatility() {
    for (int i = 0; i < NB_STRIKES; ++i) {
      PointSensitivities computedCall =
          PRICER.presentValueSensitivityModelParamsVolatility(CALLS[i], RATES_PROVIDER, VOLS).build();
      double timeToExpiry = VOLS.relativeTime(EXPIRY);
      FxRate forward = FX_PRICER.forwardFxRate(UNDERLYING[i], RATES_PROVIDER);
      double forwardRate = forward.fxRate(CURRENCY_PAIR);
      double strikeRate = CALLS[i].getStrike();
      SmileDeltaParameters smileAtTime = VOLS.getSmile().smileForExpiry(timeToExpiry);
      double[] strikes = smileAtTime.strike(forwardRate).toArray();
      double[] vols = smileAtTime.getVolatility().toArray();
      double df = RATES_PROVIDER.discountFactor(USD, PAY);
      double[] weights = weights(forwardRate, strikeRate, strikes, timeToExpiry, vols[1]);
      double[] vegas = new double[3];
      vegas[2] = BlackFormulaRepository.vega(forwardRate, strikeRate, timeToExpiry, vols[1]) * df * NOTIONAL;
      for (int j = 0; j < 3; j += 2) {
        vegas[2] -= weights[j] * NOTIONAL * df *
            BlackFormulaRepository.vega(forwardRate, strikes[j], timeToExpiry, vols[1]);
      }
      vegas[0] = weights[0] * NOTIONAL * df *
          BlackFormulaRepository.vega(forwardRate, strikes[0], timeToExpiry, vols[0]);
      vegas[1] = weights[2] * NOTIONAL * df *
          BlackFormulaRepository.vega(forwardRate, strikes[2], timeToExpiry, vols[2]);
      double[] expStrikes = new double[] {strikes[0], strikes[2], strikes[1] };
      for (int j = 0; j < 3; ++j) {
        FxOptionSensitivity sensi = (FxOptionSensitivity) computedCall.getSensitivities().get(j);
        assertThat(sensi.getSensitivity()).isCloseTo(vegas[j], offset(TOL * NOTIONAL));
        assertThat(sensi.getStrike()).isCloseTo(expStrikes[j], offset(TOL));
        assertThat(sensi.getForward()).isCloseTo(forwardRate, offset(TOL));
        assertThat(sensi.getCurrency()).isEqualTo(USD);
        assertThat(sensi.getCurrencyPair()).isEqualTo(CURRENCY_PAIR);
        assertThat(sensi.getExpiry()).isEqualTo(timeToExpiry);
      }
    }
  }
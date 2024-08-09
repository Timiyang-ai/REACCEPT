  @Test
  public void test_presentValueSensitivityModelParamsSabr() {
    PointSensitivities sensiRec =
        PRICER.presentValueSensitivityModelParamsSabr(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS).build();
    PointSensitivities sensiPay =
        PRICER.presentValueSensitivityModelParamsSabr(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS).build();
    double forward = PRICER_SWAP.parRate(RSWAP_REC, RATE_PROVIDER);
    double annuityCash = PRICER_SWAP.getLegPricer().annuityCash(RFIXED_LEG_REC, forward);
    double expiry = VOLS.relativeTime(MATURITY);
    double volatility = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(), TENOR_YEAR, RATE, forward);
    double df = RATE_PROVIDER.discountFactor(EUR, SETTLE);
    double[] volSensi =
        VOLS.getParameters().volatilityAdjoint(expiry, TENOR_YEAR, RATE, forward).getDerivatives().toArray();
    double vegaRec = df * annuityCash * BlackFormulaRepository.vega(forward + SwaptionSabrRateVolatilityDataSet.SHIFT,
        RATE + SwaptionSabrRateVolatilityDataSet.SHIFT, expiry, volatility);
    double vegaPay = -df * annuityCash * BlackFormulaRepository.vega(forward + SwaptionSabrRateVolatilityDataSet.SHIFT,
        RATE + SwaptionSabrRateVolatilityDataSet.SHIFT, expiry, volatility);
    assertSensitivity(sensiRec, SabrParameterType.ALPHA, vegaRec * volSensi[2]);
    assertSensitivity(sensiRec, SabrParameterType.BETA, vegaRec * volSensi[3]);
    assertSensitivity(sensiRec, SabrParameterType.RHO, vegaRec * volSensi[4]);
    assertSensitivity(sensiRec, SabrParameterType.NU, vegaRec * volSensi[5]);
    assertSensitivity(sensiPay, SabrParameterType.ALPHA, vegaPay * volSensi[2]);
    assertSensitivity(sensiPay, SabrParameterType.BETA, vegaPay * volSensi[3]);
    assertSensitivity(sensiPay, SabrParameterType.RHO, vegaPay * volSensi[4]);
    assertSensitivity(sensiPay, SabrParameterType.NU, vegaPay * volSensi[5]);
  }
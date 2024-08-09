  @Test
  public void test_presentValueSensitivityModelParamsSabr() {
    PointSensitivities sensiRec =
        SWAPTION_PRICER.presentValueSensitivityModelParamsSabr(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS).build();
    PointSensitivities sensiPay =
        SWAPTION_PRICER.presentValueSensitivityModelParamsSabr(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS).build();
    double forward = SWAP_PRICER.parRate(RSWAP_REC, RATE_PROVIDER);
    double pvbp = SWAP_PRICER.getLegPricer().pvbp(RSWAP_REC.getLegs(SwapLegType.FIXED).get(0), RATE_PROVIDER);
    double volatility = VOLS.volatility(SWAPTION_REC_LONG.getExpiry(),
        TENOR_YEAR, RATE, forward);
    double maturity = VOLS.relativeTime(SWAPTION_REC_LONG.getExpiry());
    double[] volSensi = VOLS.getParameters()
        .volatilityAdjoint(maturity, TENOR_YEAR, RATE, forward).getDerivatives().toArray();
    double vegaRec = pvbp * BlackFormulaRepository.vega(forward + SwaptionSabrRateVolatilityDataSet.SHIFT,
        RATE + SwaptionSabrRateVolatilityDataSet.SHIFT, maturity, volatility);
    double vegaPay = -pvbp * BlackFormulaRepository.vega(forward + SwaptionSabrRateVolatilityDataSet.SHIFT,
        RATE + SwaptionSabrRateVolatilityDataSet.SHIFT, maturity, volatility);
    assertSensitivity(sensiRec, SabrParameterType.ALPHA, vegaRec * volSensi[2], TOL);
    assertSensitivity(sensiRec, SabrParameterType.BETA, vegaRec * volSensi[3], TOL);
    assertSensitivity(sensiRec, SabrParameterType.RHO, vegaRec * volSensi[4], TOL);
    assertSensitivity(sensiRec, SabrParameterType.NU, vegaRec * volSensi[5], TOL);
    assertSensitivity(sensiPay, SabrParameterType.ALPHA, vegaPay * volSensi[2], TOL);
    assertSensitivity(sensiPay, SabrParameterType.BETA, vegaPay * volSensi[3], TOL);
    assertSensitivity(sensiPay, SabrParameterType.RHO, vegaPay * volSensi[4], TOL);
    assertSensitivity(sensiPay, SabrParameterType.NU, vegaPay * volSensi[5], TOL);
  }
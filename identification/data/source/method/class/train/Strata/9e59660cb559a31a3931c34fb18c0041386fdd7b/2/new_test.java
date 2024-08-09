  @Test
  public void test_presentValueSensitivityHullWhiteParameter() {
    DoubleArray computedRec =
        PRICER.presentValueSensitivityModelParamsHullWhite(SWAPTION_REC_LONG, RATE_PROVIDER, HW_PROVIDER);
    DoubleArray computedPay =
        PRICER.presentValueSensitivityModelParamsHullWhite(SWAPTION_PAY_SHORT, RATE_PROVIDER, HW_PROVIDER);
    DoubleArray vols = HW_PROVIDER.getParameters().getVolatility();
    int size = vols.size();
    double[] expectedRec = new double[size];
    double[] expectedPay = new double[size];
    for (int i = 0; i < size; ++i) {
      double[] volsUp = vols.toArray();
      double[] volsDw = vols.toArray();
      volsUp[i] += FD_TOL;
      volsDw[i] -= FD_TOL;
      HullWhiteOneFactorPiecewiseConstantParameters paramsUp = HullWhiteOneFactorPiecewiseConstantParameters.of(
          HW_PROVIDER.getParameters().getMeanReversion(), DoubleArray.copyOf(volsUp), HW_PROVIDER.getParameters()
              .getVolatilityTime().subArray(1, size));
      HullWhiteOneFactorPiecewiseConstantParameters paramsDw = HullWhiteOneFactorPiecewiseConstantParameters.of(
          HW_PROVIDER.getParameters().getMeanReversion(), DoubleArray.copyOf(volsDw), HW_PROVIDER.getParameters()
              .getVolatilityTime().subArray(1, size));
      HullWhiteOneFactorPiecewiseConstantParametersProvider provUp = HullWhiteOneFactorPiecewiseConstantParametersProvider
          .of(paramsUp, HW_PROVIDER.getDayCount(), HW_PROVIDER.getValuationDateTime());
      HullWhiteOneFactorPiecewiseConstantParametersProvider provDw = HullWhiteOneFactorPiecewiseConstantParametersProvider
          .of(paramsDw, HW_PROVIDER.getDayCount(), HW_PROVIDER.getValuationDateTime());
      expectedRec[i] = 0.5 * (PRICER.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, provUp).getAmount() -
          PRICER.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, provDw).getAmount()) / FD_TOL;
      expectedPay[i] = 0.5 * (PRICER.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, provUp).getAmount() -
          PRICER.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, provDw).getAmount()) / FD_TOL;
    }
    assertThat(DoubleArrayMath.fuzzyEquals(computedRec.toArray(), expectedRec, NOTIONAL * FD_TOL)).isTrue();
    assertThat(DoubleArrayMath.fuzzyEquals(computedPay.toArray(), expectedPay, NOTIONAL * FD_TOL)).isTrue();
  }
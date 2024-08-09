  @Test
  public void test_presentValueSensitivityHullWhiteParameter() {
    DoubleArray computed = PRICER.presentValueSensitivityModelParamsHullWhite(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER);
    DoubleArray vols = HW_PROVIDER.getParameters().getVolatility();
    int size = vols.size();
    double[] expected = new double[size];
    for (int i = 0; i < size; ++i) {
      double[] volsUp = vols.toArray();
      double[] volsDw = vols.toArray();
      volsUp[i] += TOL_FD;
      volsDw[i] -= TOL_FD;
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
      double priceUp = PRICER.presentValue(FUTURE_TRADE, RATE_PROVIDER, provUp, LAST_PRICE).getAmount();
      double priceDw = PRICER.presentValue(FUTURE_TRADE, RATE_PROVIDER, provDw, LAST_PRICE).getAmount();
      expected[i] = 0.5 * (priceUp - priceDw) / TOL_FD;
    }
    assertThat(DoubleArrayMath.fuzzyEquals(computed.toArray(), expected, NOTIONAL * QUANTITY * TOL_FD)).isTrue();
  }
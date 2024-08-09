public CurrencyParameterSensitivities sensitivity(
      CurrencyParameterSensitivities paramSensitivities,
      RatesProvider provider) {

    ArgChecker.notNull(paramSensitivities, "paramSensitivities");
    ArgChecker.notNull(provider, "provider");

    CurrencyParameterSensitivities result = CurrencyParameterSensitivities.empty();
    for (CurrencyParameterSensitivity paramSens : paramSensitivities.getSensitivities()) {
      // find the matching calibration info
      Curve curve = provider.findData(paramSens.getMarketDataName())
          .filter(v -> v instanceof Curve)
          .map(v -> (Curve) v)
          .orElseThrow(() -> new IllegalArgumentException(
              "Market Quote sensitivity requires curve: " + paramSens.getMarketDataName()));
      JacobianCalibrationMatrix info = curve.getMetadata().findInfo(CurveInfoType.JACOBIAN)
          .orElseThrow(() -> new IllegalArgumentException(
              "Market Quote sensitivity requires Jacobian calibration information"));

      // calculate the market quote sensitivity using the Jacobian
      DoubleMatrix jacobian = info.getJacobianMatrix();
      DoubleArray paramSensMatrix = paramSens.getSensitivity();
      DoubleArray marketQuoteSensMatrix = (DoubleArray) MATRIX_ALGEBRA.multiply(paramSensMatrix, jacobian);
      DoubleArray marketQuoteSens = marketQuoteSensMatrix;

      // split between different curves
      Map<CurveName, DoubleArray> split = info.splitValues(marketQuoteSens);
      for (Entry<CurveName, DoubleArray> entry : split.entrySet()) {
        CurveName curveName = entry.getKey();
        CurrencyParameterSensitivity maketQuoteSens = provider.findData(curveName)
            .map(c -> c.createParameterSensitivity(paramSens.getCurrency(), entry.getValue()))
            .orElse(CurrencyParameterSensitivity.of(curveName, paramSens.getCurrency(), entry.getValue()));
        result = result.combinedWith(maketQuoteSens);
      }
    }
    return result;
  }
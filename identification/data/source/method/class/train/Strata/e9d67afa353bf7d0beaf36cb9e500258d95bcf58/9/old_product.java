public CurveCurrencyParameterSensitivities sensitivity(
      CurveCurrencyParameterSensitivities paramSensitivities,
      ImmutableRatesProvider provider) {

    ArgChecker.notNull(paramSensitivities, "paramSensitivities");
    ArgChecker.notNull(provider, "provider");

    CurveCurrencyParameterSensitivities result = CurveCurrencyParameterSensitivities.empty();
    for (CurveCurrencyParameterSensitivity paramSens : paramSensitivities.getSensitivities()) {
      // find the matching calibration info
      Curve curve = provider.findCurve(paramSens.getCurveName())
          .orElseThrow(() -> new IllegalArgumentException(
              "Market Quote sensitivity requires curve: " + paramSens.getCurveName()));
      JacobianCalibrationMatrix info = curve.getMetadata().findInfo(CurveInfoType.JACOBIAN)
          .orElseThrow(() -> new IllegalArgumentException(
              "Market Quote sensitivity requires Jacobian calibration information"));

      // calculate the market quote sensitivity using the Jacobian
      DoubleMatrix2D jacobian = info.getJacobianMatrix();
      DoubleMatrix1D paramSensMatrix = paramSens.getSensitivity();
      DoubleMatrix1D marketQuoteSensMatrix = (DoubleMatrix1D) MATRIX_ALGEBRA.multiply(paramSensMatrix, jacobian);
      DoubleMatrix1D marketQuoteSens = marketQuoteSensMatrix;

      // split between different curves
      Map<CurveName, DoubleMatrix1D> split = info.splitValues(marketQuoteSens);
      for (Entry<CurveName, DoubleMatrix1D> entry : split.entrySet()) {
        // build result without curve metadata
        CurveCurrencyParameterSensitivity maketQuoteSens = CurveCurrencyParameterSensitivity.of(
            DefaultCurveMetadata.of(entry.getKey()),
            paramSens.getCurrency(),
            entry.getValue());
        result = result.combinedWith(maketQuoteSens);
      }
    }
    return result;
  }
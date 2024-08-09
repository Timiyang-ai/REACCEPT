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
      JacobianCurveCalibrationInfo info = curve.getMetadata().getCalibrationInfo()
          .orElseThrow(() -> new IllegalArgumentException(
              "Market Quote sensitivity requires Jacobian calibration information"))
          .convertTo(JacobianCurveCalibrationInfo.class);

      // calculate the market quote sensitivity using the Jacobian
      DoubleMatrix2D jacobian = info.getJacobianMatrix();
      DoubleMatrix1D paramSensMatrix = new DoubleMatrix1D(paramSens.getSensitivity());
      DoubleMatrix1D marketQuoteSensMatrix = (DoubleMatrix1D) MATRIX_ALGEBRA.multiply(paramSensMatrix, jacobian);
      double[] marketQuoteSens = marketQuoteSensMatrix.getData();

      // split between different curves
      Map<CurveName, double[]> split = info.splitValues(marketQuoteSens);
      for (Entry<CurveName, double[]> entry : split.entrySet()) {
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
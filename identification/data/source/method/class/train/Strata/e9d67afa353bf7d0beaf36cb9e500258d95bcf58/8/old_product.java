public CurveCurrencyParameterSensitivities sensitivity(
      CurveCurrencyParameterSensitivities paramSensitivities,
      CurveBuildingBlockBundle blocks) {

    ArgChecker.notNull(paramSensitivities, "paramSensitivities");
    ArgChecker.notNull(blocks, "blocks");

    CurveCurrencyParameterSensitivities result = CurveCurrencyParameterSensitivities.empty();
    for (CurveCurrencyParameterSensitivity paramSens : paramSensitivities.getSensitivities()) {
      // find the matching block
      Pair<CurveBuildingBlock, DoubleMatrix2D> block = blocks.getBlock(paramSens.getCurveName());
      // calculate the market quote sensitivity using the Jacobian from the block
      DoubleMatrix2D jacobian = block.getSecond();
      DoubleMatrix1D paramSensMatrix = new DoubleMatrix1D(paramSens.getSensitivity());
      DoubleMatrix1D marketQuoteSensMatrix = (DoubleMatrix1D) MATRIX_ALGEBRA.multiply(paramSensMatrix, jacobian);
      double[] marketQuoteSens = marketQuoteSensMatrix.getData();
      // split between different curves
      Map<CurveName, double[]> split = block.getFirst().splitValues(marketQuoteSens);
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
  @Test
  public void evaluateAndDifferentiateTest() {
    double[][][] coefsMatrix = new double[][][] {
        {{1., -3., 3., -1}, {1., 0., 0., 0.}, {1., 3., 3., 1.},},
        {{0., 5., -20., 20}, {0., 5., -10., 5}, {0., 5., 0., 0.}}};
    double[][] xKeys = new double[][] {{-2, 1, 2, 2.5}, {1.5, 7. / 3., 29. / 7., 5.}};
    int dim = 2;
    int nCoefs = 4;
    int keyLength = xKeys[0].length;
    PiecewisePolynomialResult[] pp = new PiecewisePolynomialResult[dim];
    for (int i = 0; i < dim; ++i) {
      pp[i] = new PiecewisePolynomialResult(X_VALUES, DoubleMatrix.ofUnsafe(coefsMatrix[i]), nCoefs, 1);
    }
    PiecewisePolynomialFunction1D function = new PiecewisePolynomialFunction1D();
    for (int i = 0; i < dim; ++i) {
      for (int j = 0; j < keyLength; ++j) {
        ValueDerivatives computed = function.evaluateAndDifferentiate(pp[i], xKeys[i][j]);
        double value = function.evaluate(pp[i], xKeys[i][j]).get(0);
        double deriv = function.differentiate(pp[i], xKeys[i][j]).get(0);
        assertThat(computed.getValue()).isCloseTo(value, offset(EPS));
        assertThat(computed.getDerivatives().size()).isEqualTo(1);
        assertThat(computed.getDerivative(0)).isCloseTo(deriv, offset(EPS));
      }
    }
  }
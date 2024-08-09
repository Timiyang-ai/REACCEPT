public ValueDerivatives evaluateAndDifferentiate(PiecewisePolynomialResult pp, double xKey) {
    ArgChecker.notNull(pp, "null pp");
    ArgChecker.isFalse(Double.isNaN(xKey), "xKey containing NaN");
    ArgChecker.isFalse(Double.isInfinite(xKey), "xKey containing Infinity");

    if (pp.getDimensions() > 1) {
      throw new UnsupportedOperationException();
    }

    DoubleArray knots = pp.getKnots();
    int nKnots = knots.size();
    int interval = FunctionUtils.getLowerBoundIndex(knots, xKey);
    if (interval == nKnots - 1) {
      interval--; // there is 1 less interval that knots
    }

    double s = xKey - knots.get(interval);
    DoubleArray coefs = pp.getCoefMatrix().row(interval);
    int nCoefs = coefs.size();

    double resValue = coefs.get(0);
    double resDeriv = coefs.get(0) * (nCoefs - 1);
    for (int i = 1; i < nCoefs - 1; i++) {
      resValue *= s;
      resValue += coefs.get(i);
      resDeriv *= s;
      resDeriv += coefs.get(i) * (nCoefs - i - 1);
      ArgChecker.isFalse(Double.isInfinite(resValue), "Too large input");
      ArgChecker.isFalse(Double.isNaN(resValue), "Too large input");
    }
    resValue *= s;
    resValue += coefs.get(nCoefs - 1);

    return ValueDerivatives.of(resValue, DoubleArray.of(resDeriv));
  }
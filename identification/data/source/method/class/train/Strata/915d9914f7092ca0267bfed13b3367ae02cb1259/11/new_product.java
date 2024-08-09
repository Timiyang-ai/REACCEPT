private double discountFactor(double relativeYearFraction) {
    // short cut rate lookup
    if (relativeYearFraction == 0) {
      return 1.0;
    }
    return Math.exp(-relativeYearFraction * curve.yValue(relativeYearFraction));
  }
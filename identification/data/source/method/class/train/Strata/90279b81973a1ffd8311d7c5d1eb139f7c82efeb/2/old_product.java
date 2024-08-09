double discountFactor(double relativeYearFraction) {
    // read discount factor directly off curve
    return curve.yValue(relativeYearFraction);
  }
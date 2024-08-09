private double discountFactor(double relativeYearFraction) {
    // convert zero rate to discount factor
    return Math.exp(-relativeYearFraction * curve.yValue(relativeYearFraction));
  }
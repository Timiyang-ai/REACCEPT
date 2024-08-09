@Override
  public double discountFactor(double yearFraction) {
    // convert zero rate to discount factor
    return Math.exp(-yearFraction * curve.yValue(yearFraction));
  }
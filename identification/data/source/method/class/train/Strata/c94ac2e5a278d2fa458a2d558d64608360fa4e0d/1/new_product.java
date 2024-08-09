@Override
  public double discountFactor(double relativeYearFraction) {
    // convert zero rate periodically compounded to discount factor
    return Math.pow(1d + curve.yValue(relativeYearFraction) / frequency, -relativeYearFraction * frequency);
  }
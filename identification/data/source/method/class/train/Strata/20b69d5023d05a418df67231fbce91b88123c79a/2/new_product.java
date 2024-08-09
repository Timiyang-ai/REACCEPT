@Override
  public double volatility(double expiry, double tenor, double strike, double forwardRate) {
    return parameters.volatility(expiry, tenor, strike, forwardRate);
  }
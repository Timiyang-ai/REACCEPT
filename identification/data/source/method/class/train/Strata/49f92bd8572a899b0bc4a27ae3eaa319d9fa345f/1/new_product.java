@Override
  public double volatility(double expiry, double tenor, double strike, double forwardRate) {
    return surface.zValue(expiry, tenor);
  }
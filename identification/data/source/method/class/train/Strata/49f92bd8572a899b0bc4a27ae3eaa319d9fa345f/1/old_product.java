@Override
  public double volatility(ZonedDateTime expiryDate, double tenor, double strike, double forwardRate) {
    double expiryTime = relativeTime(expiryDate);
    return surface.zValue(expiryTime, tenor);
  }
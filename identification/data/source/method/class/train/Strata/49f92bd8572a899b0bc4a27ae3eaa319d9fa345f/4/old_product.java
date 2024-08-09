@Override
  public double volatility(ZonedDateTime expiryDate, double tenor, double strike, double forwardRate) {
    double expiryTime = relativeTime(expiryDate);
    double volatility = surface.zValue(expiryTime, tenor);
    return volatility;
  }
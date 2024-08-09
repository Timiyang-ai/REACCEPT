public double volatility(ZonedDateTime expiryDate, double tenor, double strike, double forwardRate) {
    double expiryTime = relativeTime(expiryDate);
    return parameters.getVolatility(expiryTime, tenor, strike, forwardRate);
  }
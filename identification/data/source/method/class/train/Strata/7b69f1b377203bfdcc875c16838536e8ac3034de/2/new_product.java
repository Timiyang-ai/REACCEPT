@Override
  public double price(double expiry, PutCall putCall, double strike, double forward, double volatility) {
    return BlackFormulaRepository.price(forward, strike, expiry, volatility, putCall.isCall());
  }
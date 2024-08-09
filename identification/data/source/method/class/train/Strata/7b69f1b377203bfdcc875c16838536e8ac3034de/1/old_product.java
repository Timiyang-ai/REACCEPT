@Override
  public double price(double expiry, PutCall putCall, double strike, double forwardRate, double volatility) {
    return NormalFormulaRepository.price(forwardRate, strike, expiry, volatility, putCall);
  }
@Override
  public double price(double expiry, PutCall putCall, double strike, double forwardRate, double volatility) {
    return BlackFormulaRepository.price(forwardRate, strike, expiry, volatility, putCall.isCall());
  }
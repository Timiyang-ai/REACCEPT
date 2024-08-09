public double price(double strike, PutCall putCall) {
    // Uses Hagan et al SABR function.
    if (strike <= cutOffStrike) {
      double volatility = sabrFunction.volatility(forward, strike, timeToExpiry, sabrData);
      return BlackFormulaRepository.price(forward, strike, timeToExpiry, volatility, putCall.isCall());
    }
    // Uses extrapolation for call.
    double price = extrapolation(strike);
    if (putCall.isPut()) { // Put by call/put parity
      price -= (forward - strike);
    }
    return price;
  }
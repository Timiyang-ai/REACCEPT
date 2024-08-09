public double price(double strike, PutCall putCall) {
    double price = 0d;
    if (strike <= cutOffStrike) { // Uses Hagan et al SABR function.
      double volatility = sabrFunction.getVolatility(forward, strike, timeToExpiry, sabrData);
      price = BlackFormulaRepository.price(forward, strike, timeToExpiry, volatility, putCall.isCall());
    } else { // Uses extrapolation for call.
      price = extrapolation(strike);
      if (putCall.isPut()) { // Put by call/put parity
        price -= (forward - strike);
      }
    }
    return price;
  }
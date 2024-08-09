public double theta(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    double timeToExpiry = volatilities.relativeTime(option.getExpiry());
    if (timeToExpiry <= 0d) {
      return 0d;
    }
    ResolvedFxSingle underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    CurrencyPair strikePair = underlying.getCurrencyPair();
    double forwardRate = forward.fxRate(strikePair);
    double strikeRate = option.getStrike();
    double volatility = volatilities.volatility(
        strikePair, option.getExpiry(), strikeRate, forwardRate);
    double fwdTheta = BlackFormulaRepository.driftlessTheta(forwardRate, strikeRate, timeToExpiry, volatility);
    double discountFactor = ratesProvider.discountFactor(option.getCounterCurrency(), underlying.getPaymentDate());
    return discountFactor * fwdTheta;
  }
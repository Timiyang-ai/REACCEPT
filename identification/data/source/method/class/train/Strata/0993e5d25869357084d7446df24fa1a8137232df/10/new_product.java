public double vega(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    double timeToExpiry = volatilityProvider.relativeTime(option.getExpiry());
    if (timeToExpiry <= 0d) {
      return 0d;
    }
    ResolvedFxSingle underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    CurrencyPair strikePair = underlying.getCurrencyPair();
    double forwardRate = forward.fxRate(strikePair);
    double strikeRate = option.getStrike();
    double volatility = volatilityProvider.volatility(strikePair, option.getExpiry(), strikeRate, forwardRate);
    double fwdVega = BlackFormulaRepository.vega(forwardRate, strikeRate, timeToExpiry, volatility);
    double discountFactor = ratesProvider.discountFactor(option.getCounterCurrency(), underlying.getPaymentDate());
    return discountFactor * fwdVega;
  }
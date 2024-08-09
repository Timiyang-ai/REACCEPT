public double vega(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    double timeToExpiry = volatilityProvider.relativeTime(option.getExpiryDateTime());
    if (timeToExpiry <= 0d) {
      return 0d;
    }
    FxSingle underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    FxRate strike = option.getStrike();
    CurrencyPair strikePair = strike.getPair();
    double forwardRate = forward.fxRate(strikePair);
    double strikeRate = strike.fxRate(strikePair);
    double volatility = volatilityProvider.getVolatility(
        strikePair, option.getExpiryDateTime(), strikeRate, forwardRate);
    double fwdVega = BlackFormulaRepository.vega(forwardRate, strikeRate, timeToExpiry, volatility);
    double discountFactor = ratesProvider.discountFactor(option.getPayoffCurrency(), underlying.getPaymentDate());
    return discountFactor * fwdVega;
  }
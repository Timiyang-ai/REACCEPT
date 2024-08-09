public double impliedVolatility(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    double timeToExpiry =
        volatilityProvider.relativeTime(option.getExpiryDate(), option.getExpiryTime(), option.getExpiryZone());
    if (timeToExpiry <= 0d) {
      throw new IllegalArgumentException("valuation date is after option's expiry date.");
    }
    FxRate forward = fxPricer.forwardFxRate(option.getUnderlying(), ratesProvider);
    FxRate strike = option.getStrike();
    CurrencyPair strikePair = strike.getPair();
    return volatilityProvider.getVolatility(
        strikePair, option.getExpiryDate(), strike.fxRate(strikePair), forward.fxRate(strikePair));
  }
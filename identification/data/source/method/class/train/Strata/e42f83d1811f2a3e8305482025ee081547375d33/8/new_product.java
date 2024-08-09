public double impliedVolatility(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    double timeToExpiry = volatilities.relativeTime(option.getExpiry());
    if (timeToExpiry <= 0d) {
      throw new IllegalArgumentException("valuation is after option's expiry.");
    }
    FxRate forward = fxPricer.forwardFxRate(option.getUnderlying(), ratesProvider);
    CurrencyPair strikePair = option.getUnderlying().getCurrencyPair();
    return volatilities.volatility(
        strikePair, option.getExpiry(), option.getStrike(), forward.fxRate(strikePair));
  }
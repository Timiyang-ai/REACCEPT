public double gamma(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    double timeToExpiry = volatilityProvider.relativeTime(option.getExpiry());
    if (timeToExpiry <= 0d) {
      return 0d;
    }
    ResolvedFxSingle underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    FxRate strike = option.getStrike();
    CurrencyPair strikePair = strike.getPair();
    double forwardRate = forward.fxRate(strikePair);
    double strikeRate = strike.fxRate(strikePair);
    double volatility = volatilityProvider.getVolatility(
        strikePair, option.getExpiry(), strikeRate, forwardRate);
    double forwardGamma = BlackFormulaRepository.gamma(forwardRate, strikeRate, timeToExpiry, volatility);
    double discountFactor = ratesProvider.discountFactor(option.getPayoffCurrency(), underlying.getPaymentDate());
    double fwdRateSpotSensitivity = fxPricer.forwardFxRateSpotSensitivity(underlying, ratesProvider);
    return forwardGamma * discountFactor * fwdRateSpotSensitivity * fwdRateSpotSensitivity;
  }
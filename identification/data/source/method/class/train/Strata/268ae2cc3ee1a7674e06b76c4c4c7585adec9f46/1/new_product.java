public double gamma(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    double timeToExpiry =
        volatilityProvider.relativeTime(option.getExpiryDate(), option.getExpiryTime(), option.getExpiryZone());
    if (timeToExpiry <= 0d) {
      return 0d;
    }
    Fx underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    FxRate strike = option.getStrike();
    CurrencyPair strikePair = strike.getPair();
    double forwardRate = forward.fxRate(strikePair);
    double strikeRate = strike.fxRate(strikePair);
    double volatility = volatilityProvider.getVolatility(strikePair, option.getExpiryDate(), strikeRate, forwardRate);
    double forwardGamma = BlackFormulaRepository.gamma(forwardRate, strikeRate, timeToExpiry, volatility);
    double discountFactor = ratesProvider.discountFactor(option.getPayoffCurrency(), underlying.getPaymentDate());
    double fwdRateSpotSensitivity = fxPricer.forwardFxRateSpotSensitivity(underlying, ratesProvider);
    return forwardGamma * discountFactor * fwdRateSpotSensitivity * fwdRateSpotSensitivity;
  }
public PointSensitivityBuilder presentValueSensitivityBlackVolatility(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    if (volatilityProvider.relativeTime(option.getExpiryDate(), option.getExpiryTime(), option.getExpiryZone()) <= 0d) {
      return PointSensitivityBuilder.none();
    }
    Fx underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    FxRate strike = option.getStrike();
    CurrencyPair strikePair = strike.getPair();
    CurrencyAmount valueVega = presentValueVega(option, ratesProvider, volatilityProvider);
    return FxOptionSensitivity.of(strikePair, option.getExpiryDate(), strike.fxRate(strikePair),
        forward.fxRate(strikePair), valueVega.getCurrency(), valueVega.getAmount());
  }
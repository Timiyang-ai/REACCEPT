public PointSensitivityBuilder presentValueSensitivityBlackVolatility(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    if (volatilityProvider.relativeTime(option.getExpiry()) <= 0d) {
      return PointSensitivityBuilder.none();
    }
    ResolvedFxSingle underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    FxRate strike = option.getStrike();
    CurrencyPair strikePair = strike.getPair();
    CurrencyAmount valueVega = presentValueVega(option, ratesProvider, volatilityProvider);
    return FxOptionSensitivity.of(strikePair, option.getExpiry(), strike.fxRate(strikePair),
        forward.fxRate(strikePair), valueVega.getCurrency(), valueVega.getAmount());
  }
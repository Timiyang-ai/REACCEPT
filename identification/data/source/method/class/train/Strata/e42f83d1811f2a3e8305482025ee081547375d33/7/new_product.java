public PointSensitivityBuilder presentValueSensitivityModelParamsVolatility(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    if (volatilityProvider.relativeTime(option.getExpiry()) <= 0d) {
      return PointSensitivityBuilder.none();
    }
    ResolvedFxSingle underlying = option.getUnderlying();
    FxRate forward = fxPricer.forwardFxRate(underlying, ratesProvider);
    CurrencyPair strikePair = underlying.getCurrencyPair();
    CurrencyAmount valueVega = presentValueVega(option, ratesProvider, volatilityProvider);
    return FxOptionSensitivity.of(strikePair, option.getExpiry(), option.getStrike(), forward.fxRate(strikePair),
        valueVega.getCurrency(), valueVega.getAmount());
  }
public double delta(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    Fx underlying = option.getUnderlying();
    double undiscountedDelta = undiscountedDelta(option, ratesProvider, volatilityProvider);
    double discountFactor = ratesProvider.discountFactor(option.getPayoffCurrency(), underlying.getPaymentDate());
    double fwdRateSpotSensitivity = fxPricer.forwardFxRateSpotSensitivity(underlying, ratesProvider);
    return undiscountedDelta * discountFactor * fwdRateSpotSensitivity;
  }
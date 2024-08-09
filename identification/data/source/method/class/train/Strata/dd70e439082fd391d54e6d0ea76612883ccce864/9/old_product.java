public PointSensitivities presentValueSensitivity(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    Fx underlying = option.getUnderlying();
    double fwdDelta = forwardDelta(option, ratesProvider, volatilityProvider);
    double discountFactor = ratesProvider.discountFactor(option.getPayoffCurrency(), underlying.getPaymentDate());
    double notional = signedNotional(option);
    PointSensitivityBuilder fwdSensi = fxPricer.forwardFxRatePointSensitivity(underlying, ratesProvider)
        .multipliedBy(notional * discountFactor * fwdDelta);
    double fwdPrice = forwardPrice(option, ratesProvider, volatilityProvider);
    PointSensitivityBuilder dscSensi = ratesProvider.discountFactors(option.getPayoffCurrency())
        .zeroRatePointSensitivity(underlying.getPaymentDate()).multipliedBy(notional * fwdPrice);
    return fwdSensi.combinedWith(dscSensi).build();
  }
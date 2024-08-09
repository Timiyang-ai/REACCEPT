public PointSensitivities presentValueSensitivityRates(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    if (volatilityProvider.relativeTime(option.getExpiry()) < 0d) {
      return PointSensitivities.empty();
    }
    ResolvedFxSingle underlying = option.getUnderlying();
    double fwdDelta = undiscountedDelta(option, ratesProvider, volatilityProvider);
    double discountFactor = ratesProvider.discountFactor(option.getCounterCurrency(), underlying.getPaymentDate());
    double notional = signedNotional(option);
    PointSensitivityBuilder fwdSensi = fxPricer.forwardFxRatePointSensitivity(
        option.getPutCall().isCall() ? underlying : underlying.inverse(), ratesProvider)
        .multipliedBy(notional * discountFactor * fwdDelta);
    double fwdPrice = undiscountedPrice(option, ratesProvider, volatilityProvider);
    PointSensitivityBuilder dscSensi = ratesProvider.discountFactors(option.getCounterCurrency())
        .zeroRatePointSensitivity(underlying.getPaymentDate()).multipliedBy(notional * fwdPrice);
    return fwdSensi.combinedWith(dscSensi).build().convertedTo(option.getCounterCurrency(), ratesProvider);
  }
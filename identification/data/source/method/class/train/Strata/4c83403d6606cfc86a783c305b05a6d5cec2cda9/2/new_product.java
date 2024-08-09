public PointSensitivityBuilder forwardFxRatePointSensitivity(ResolvedFxSingle fx, RatesProvider provider) {
    FxForwardRates fxForwardRates = provider.fxForwardRates(fx.getCurrencyPair());
    PointSensitivityBuilder forwardFxRatePointSensitivity = fxForwardRates.ratePointSensitivity(
        fx.getReceiveCurrencyAmount().getCurrency(), fx.getPaymentDate());
    return forwardFxRatePointSensitivity;
  }
public double forwardFxRateSpotSensitivity(ResolvedFxSingle fx, RatesProvider provider) {
    FxForwardRates fxForwardRates = provider.fxForwardRates(fx.getCurrencyPair());
    double forwardRateSpotSensitivity = fxForwardRates.rateFxSpotSensitivity(
        fx.getReceiveCurrencyAmount().getCurrency(), fx.getPaymentDate());
    return forwardRateSpotSensitivity;
  }
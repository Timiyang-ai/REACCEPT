public double forwardFxRateSpotSensitivity(FxSingleProduct product, RatesProvider provider) {
    ExpandedFxSingle fx = product.expand();
    FxForwardRates fxForwardRates = provider.fxForwardRates(fx.getCurrencyPair());
    double forwardRateSpotSensitivity = fxForwardRates.rateFxSpotSensitivity(
        fx.getReceiveCurrencyAmount().getCurrency(), fx.getPaymentDate());
    return forwardRateSpotSensitivity;
  }
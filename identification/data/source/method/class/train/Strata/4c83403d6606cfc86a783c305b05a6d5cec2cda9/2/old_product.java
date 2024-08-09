public PointSensitivityBuilder forwardFxRatePointSensitivity(FxSingleProduct product, RatesProvider provider) {
    ExpandedFxSingle fx = product.expand();
    FxForwardRates fxForwardRates = provider.fxForwardRates(fx.getCurrencyPair());
    PointSensitivityBuilder forwardFxRatePointSensitivity = fxForwardRates.ratePointSensitivity(
        fx.getReceiveCurrencyAmount().getCurrency(), fx.getPaymentDate());
    return forwardFxRatePointSensitivity;
  }
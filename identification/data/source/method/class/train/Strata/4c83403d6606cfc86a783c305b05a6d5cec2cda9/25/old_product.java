public PointSensitivities parSpreadSensitivity(FxSwapProduct product, RatesProvider provider) {
    ExpandedFxSwap fx = product.expand();
    Payment counterPaymentNear = fx.getNearLeg().getCounterCurrencyPayment();
    MultiCurrencyAmount pv = presentValue(fx, provider);
    double pvCounterCcy = pv.convertedTo(counterPaymentNear.getCurrency(), provider).getAmount();
    double dfEnd = provider.discountFactor(counterPaymentNear.getCurrency(), fx.getFarLeg().getPaymentDate());
    double notionalBaseCcy = fx.getNearLeg().getBaseCurrencyPayment().getAmount();
    double ps = -pvCounterCcy / (notionalBaseCcy * dfEnd);
    // backward sweep
    double psBar = 1d;
    double pvCounterCcyBar = -1d / (notionalBaseCcy * dfEnd) * psBar;
    double dfEndBar = -ps / dfEnd * psBar;
    ZeroRateSensitivity ddfEnddr = provider.discountFactors(counterPaymentNear.getCurrency())
        .zeroRatePointSensitivity(fx.getFarLeg().getPaymentDate());
    PointSensitivities result = ddfEnddr.multipliedBy(dfEndBar).build();
    PointSensitivities dpvdr = presentValueSensitivity(fx, provider);
    PointSensitivities dpvdrConverted = dpvdr.convertedTo(counterPaymentNear.getCurrency(), provider);
    return result.combinedWith(dpvdrConverted.multipliedBy(pvCounterCcyBar));
  }
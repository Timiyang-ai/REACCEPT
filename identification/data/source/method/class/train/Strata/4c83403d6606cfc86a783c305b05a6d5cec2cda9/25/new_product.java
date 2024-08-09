public PointSensitivities parSpreadSensitivity(ResolvedFxSwap swap, RatesProvider provider) {
    Payment counterPaymentNear = swap.getNearLeg().getCounterCurrencyPayment();
    MultiCurrencyAmount pv = presentValue(swap, provider);
    double pvCounterCcy = pv.convertedTo(counterPaymentNear.getCurrency(), provider).getAmount();
    double dfEnd = provider.discountFactor(counterPaymentNear.getCurrency(), swap.getFarLeg().getPaymentDate());
    double notionalBaseCcy = swap.getNearLeg().getBaseCurrencyPayment().getAmount();
    double ps = -pvCounterCcy / (notionalBaseCcy * dfEnd);
    // backward sweep
    double psBar = 1d;
    double pvCounterCcyBar = -1d / (notionalBaseCcy * dfEnd) * psBar;
    double dfEndBar = -ps / dfEnd * psBar;
    ZeroRateSensitivity ddfEnddr = provider.discountFactors(counterPaymentNear.getCurrency())
        .zeroRatePointSensitivity(swap.getFarLeg().getPaymentDate());
    PointSensitivities result = ddfEnddr.multipliedBy(dfEndBar).build();
    PointSensitivities dpvdr = presentValueSensitivity(swap, provider);
    PointSensitivities dpvdrConverted = dpvdr.convertedTo(counterPaymentNear.getCurrency(), provider);
    return result.combinedWith(dpvdrConverted.multipliedBy(pvCounterCcyBar));
  }
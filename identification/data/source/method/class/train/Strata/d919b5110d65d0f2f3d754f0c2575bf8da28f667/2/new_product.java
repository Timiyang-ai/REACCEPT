public double parSpread(FxSwapProduct product, RatesProvider provider) {
    ExpandedFxSwap fx = product.expand();
    Payment counterPaymentNear = fx.getNearLeg().getCounterCurrencyPayment();
    MultiCurrencyAmount pv = presentValue(fx, provider);
    double pvCounterCcy = pv.convertedTo(counterPaymentNear.getCurrency(), provider).getAmount();
    double dfEnd = provider.discountFactor(counterPaymentNear.getCurrency(), fx.getFarLeg().getPaymentDate());
    double notionalBaseCcy = fx.getNearLeg().getBaseCurrencyPayment().getAmount();
    return -pvCounterCcy / (notionalBaseCcy * dfEnd);
  }
public double parSpread(FxSingleProduct product, RatesProvider provider) {
    ExpandedFxSingle fx = product.expand();
    Payment basePayment = fx.getBaseCurrencyPayment();
    Payment counterPayment = fx.getCounterCurrencyPayment();
    MultiCurrencyAmount pv = presentValue(fx, provider);
    double pvCounterCcy = pv.convertedTo(counterPayment.getCurrency(), provider).getAmount();
    double dfEnd = provider.discountFactor(counterPayment.getCurrency(), fx.getPaymentDate());
    double notionalBaseCcy = basePayment.getAmount();
    return pvCounterCcy / (notionalBaseCcy * dfEnd);
  }
public double parSpread(ResolvedFxSwap swap, RatesProvider provider) {
    Payment counterPaymentNear = swap.getNearLeg().getCounterCurrencyPayment();
    MultiCurrencyAmount pv = presentValue(swap, provider);
    double pvCounterCcy = pv.convertedTo(counterPaymentNear.getCurrency(), provider).getAmount();
    double dfEnd = provider.discountFactor(counterPaymentNear.getCurrency(), swap.getFarLeg().getPaymentDate());
    double notionalBaseCcy = swap.getNearLeg().getBaseCurrencyPayment().getAmount();
    return -pvCounterCcy / (notionalBaseCcy * dfEnd);
  }
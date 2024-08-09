public MultiCurrencyAmount presentValue(FxSingleProduct product, RatesProvider provider) {
    ExpandedFxSingle fx = product.expand();
    if (provider.getValuationDate().isAfter(fx.getPaymentDate())) {
      return MultiCurrencyAmount.empty();
    }
    CurrencyAmount pv1 = paymentPricer.presentValue(fx.getBaseCurrencyPayment(), provider);
    CurrencyAmount pv2 = paymentPricer.presentValue(fx.getCounterCurrencyPayment(), provider);
    return MultiCurrencyAmount.of(pv1, pv2);
  }
public MultiCurrencyAmount presentValue(ResolvedFxSingle fx, RatesProvider provider) {
    if (provider.getValuationDate().isAfter(fx.getPaymentDate())) {
      return MultiCurrencyAmount.empty();
    }
    CurrencyAmount pv1 = paymentPricer.presentValue(fx.getBaseCurrencyPayment(), provider);
    CurrencyAmount pv2 = paymentPricer.presentValue(fx.getCounterCurrencyPayment(), provider);
    return MultiCurrencyAmount.of(pv1, pv2);
  }
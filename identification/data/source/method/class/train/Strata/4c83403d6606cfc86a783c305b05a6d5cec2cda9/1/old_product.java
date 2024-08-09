public MultiCurrencyAmount currentCash(FxSingleProduct product, LocalDate valuationDate) {
    ExpandedFxSingle fx = product.expand();
    if (valuationDate.isEqual(fx.getPaymentDate())) {
      return MultiCurrencyAmount.of(fx.getBaseCurrencyPayment().getValue(), fx.getCounterCurrencyPayment().getValue());
    }
    return MultiCurrencyAmount.empty();
  }
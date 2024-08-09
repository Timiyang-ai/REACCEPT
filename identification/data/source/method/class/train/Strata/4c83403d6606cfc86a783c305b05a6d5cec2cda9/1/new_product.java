public MultiCurrencyAmount currentCash(ResolvedFxSingle fx, LocalDate valuationDate) {
    if (valuationDate.isEqual(fx.getPaymentDate())) {
      return MultiCurrencyAmount.of(fx.getBaseCurrencyPayment().getValue(), fx.getCounterCurrencyPayment().getValue());
    }
    return MultiCurrencyAmount.empty();
  }
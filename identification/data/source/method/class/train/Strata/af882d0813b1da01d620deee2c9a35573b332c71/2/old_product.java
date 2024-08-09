@Override
  public ResolvedFxSingle resolve(ReferenceData refData) {
    return ResolvedFxSingle.of(
        Payment.of(baseCurrencyAmount, paymentDate),
        Payment.of(counterCurrencyAmount, paymentDate));
  }
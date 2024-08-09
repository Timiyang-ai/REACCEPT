@Override
  public ResolvedFxSingle resolve(ReferenceData refData) {
    if (paymentDateAdjustment == null) {
      return ResolvedFxSingle.of(baseCurrencyPayment, counterCurrencyPayment);
    }
    DateAdjuster adjuster = paymentDateAdjustment.resolve(refData);
    return ResolvedFxSingle.of(baseCurrencyPayment.adjustDate(adjuster), counterCurrencyPayment.adjustDate(adjuster));
  }
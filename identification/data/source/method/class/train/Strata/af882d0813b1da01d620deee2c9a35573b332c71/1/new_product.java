@Override
  public ResolvedFxSingle resolve(ReferenceData refData) {
    LocalDate date = paymentDateAdjustment != null ? paymentDateAdjustment.adjust(paymentDate, refData) : paymentDate;
    return ResolvedFxSingle.of(
        Payment.of(baseCurrencyAmount, date),
        Payment.of(counterCurrencyAmount, date));
  }
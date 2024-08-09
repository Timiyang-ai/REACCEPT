@Override
  public ResolvedFxNdf resolve(ReferenceData refData) {
    return ResolvedFxNdf.builder()
        .settlementCurrencyNotional(settlementCurrencyNotional)
        .agreedFxRate(agreedFxRate)
        .paymentDate(paymentDate)
        .index(index)
        .build();
  }
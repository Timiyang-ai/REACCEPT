@Override
  public ResolvedFxNdf resolve(ReferenceData refData) {
    LocalDate fixingDate = index.calculateFixingFromMaturity(paymentDate, refData);
    return ResolvedFxNdf.builder()
        .settlementCurrencyNotional(settlementCurrencyNotional)
        .agreedFxRate(agreedFxRate)
        .observation(FxIndexObservation.of(index, fixingDate, refData))
        .paymentDate(paymentDate)
        .build();
  }
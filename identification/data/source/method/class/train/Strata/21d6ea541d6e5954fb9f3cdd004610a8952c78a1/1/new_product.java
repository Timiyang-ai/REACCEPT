@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    ImmutableList<RatePaymentPeriod> adjusted = paymentPeriods.stream()
        .map(pp -> pp.adjustPaymentDate(paymentBusinessDayAdjustment))
        .collect(toImmutableList());
    return ResolvedSwapLeg.builder()
        .type(type)
        .payReceive(payReceive)
        .paymentPeriods(adjusted)
        .paymentEvents(createEvents(refData, adjusted))
        .build();
  }
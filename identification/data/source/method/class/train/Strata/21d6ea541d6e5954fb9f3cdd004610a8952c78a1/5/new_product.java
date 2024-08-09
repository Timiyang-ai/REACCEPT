@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    DateAdjuster paymentDateAdjuster = paymentBusinessDayAdjustment.toDateAdjuster(refData);
    ImmutableList<RatePaymentPeriod> adjusted = paymentPeriods.stream()
        .map(pp -> pp.adjustPaymentDate(paymentDateAdjuster))
        .collect(toImmutableList());
    return ResolvedSwapLeg.builder()
        .type(type)
        .payReceive(payReceive)
        .paymentPeriods(adjusted)
        .paymentEvents(createEvents(adjusted, paymentDateAdjuster, refData))
        .build();
  }
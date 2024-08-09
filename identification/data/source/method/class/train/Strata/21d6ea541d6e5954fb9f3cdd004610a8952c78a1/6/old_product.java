@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    DateAdjuster paymentDateAdjuster = paymentBusinessDayAdjustment.resolve(refData);
    ImmutableList<NotionalPaymentPeriod> adjusted = paymentPeriods.stream()
        .map(pp -> pp.adjustPaymentDate(paymentDateAdjuster))
        .collect(toImmutableList());
    return ResolvedSwapLeg.builder()
        .type(type)
        .payReceive(payReceive)
        .paymentPeriods(adjusted)
        .paymentEvents(createEvents(adjusted, paymentDateAdjuster, refData))
        .build();
  }
@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    DateAdjuster paymentDateAdjuster = paymentBusinessDayAdjustment.resolve(refData);
    ImmutableList<NotionalPaymentPeriod> adjusted = paymentPeriods.stream()
        .map(pp -> pp.adjustPaymentDate(paymentDateAdjuster))
        .collect(toImmutableList());
    ImmutableList<SwapPaymentEvent> payEvents = createEvents(adjusted, paymentDateAdjuster, refData);
    return new ResolvedSwapLeg(type, payReceive, adjusted, payEvents, currency);
  }
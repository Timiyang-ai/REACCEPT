@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    DayCount dayCount = calculation.getDayCount();
    Schedule resolvedAccruals = accrualSchedule.createSchedule();
    Schedule resolvedPayments = paymentSchedule.createSchedule(resolvedAccruals);
    List<RateAccrualPeriod> accrualPeriods = calculation.expand(resolvedAccruals, resolvedPayments);
    List<RatePaymentPeriod> payPeriods = paymentSchedule.createPaymentPeriods(
        resolvedAccruals, resolvedPayments, accrualPeriods, dayCount, notionalSchedule, payReceive);
    return ResolvedSwapLeg.builder()
        .type(getType())
        .payReceive(payReceive)
        .paymentPeriods(payPeriods)
        .paymentEvents(notionalSchedule.createEvents(payPeriods, getStartDate().adjusted()))
        .build();
  }
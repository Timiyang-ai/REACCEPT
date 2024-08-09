@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    DayCount dayCount = calculation.getDayCount();
    Schedule resolvedAccruals = accrualSchedule.createSchedule(refData);
    Schedule resolvedPayments = paymentSchedule.createSchedule(resolvedAccruals, refData);
    List<RateAccrualPeriod> accrualPeriods = calculation.createAccrualPeriods(resolvedAccruals, resolvedPayments, refData);
    List<NotionalPaymentPeriod> payPeriods = paymentSchedule.createPaymentPeriods(
        resolvedAccruals, resolvedPayments, accrualPeriods, dayCount, notionalSchedule, payReceive, refData);
    LocalDate startDate = accrualPeriods.get(0).getStartDate();
    ImmutableList<SwapPaymentEvent> payEvents = notionalSchedule.createEvents(payPeriods, startDate, refData);
    return new ResolvedSwapLeg(getType(), payReceive, payPeriods, payEvents, getCurrency());
  }
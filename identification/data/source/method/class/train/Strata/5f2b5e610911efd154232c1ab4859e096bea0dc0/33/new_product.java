@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    Schedule resolvedAccruals = accrualSchedule.createSchedule(refData);
    Schedule resolvedPayments = paymentSchedule.createSchedule(resolvedAccruals, refData);
    List<SwapPaymentPeriod> payPeriods = createPaymentPeriods(resolvedPayments, refData);
    return new ResolvedSwapLeg(getType(), payReceive, payPeriods, ImmutableList.of(), currency);
  }
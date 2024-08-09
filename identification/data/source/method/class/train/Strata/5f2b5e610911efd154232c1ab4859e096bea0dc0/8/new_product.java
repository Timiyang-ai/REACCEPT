@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    Schedule resolvedAccruals = accrualSchedule.createSchedule(refData);
    Schedule resolvedPayments = paymentSchedule.createSchedule(resolvedAccruals, refData);
    List<PaymentPeriod> payPeriods = createPaymentPeriods(resolvedPayments, refData);
    return ResolvedSwapLeg.builder()
        .type(getType())
        .payReceive(payReceive)
        .paymentPeriods(payPeriods)
        .build();
  }
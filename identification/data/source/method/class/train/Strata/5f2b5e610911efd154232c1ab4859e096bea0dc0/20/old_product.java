@Override
  public ResolvedSwapLeg resolve(ReferenceData refData) {
    Schedule resolvedAccruals = accrualSchedule.createSchedule();
    Schedule resolvedPayments = paymentSchedule.createSchedule(resolvedAccruals);
    List<PaymentPeriod> payPeriods = createPaymentPeriods(resolvedPayments);
    return ResolvedSwapLeg.builder()
        .type(getType())
        .payReceive(payReceive)
        .paymentPeriods(payPeriods)
        .build();
  }
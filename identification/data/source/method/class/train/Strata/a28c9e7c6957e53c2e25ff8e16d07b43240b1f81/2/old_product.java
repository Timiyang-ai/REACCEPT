public Schedule createSchedule(Schedule accrualSchedule, ReferenceData refData) {
    // payment frequency of Term absorbs everything
    if (paymentFrequency.equals(Frequency.TERM)) {
      return accrualSchedule.mergeToTerm();
    }
    // derive schedule, retaining stubs as payment periods
    int accrualPeriodsPerPayment = paymentFrequency.exactDivide(accrualSchedule.getFrequency());
    boolean rollForwards = !accrualSchedule.getInitialStub().isPresent();
    Schedule paySchedule = accrualSchedule.mergeRegular(accrualPeriodsPerPayment, rollForwards);
    if (businessDayAdjustment != null) {
      return paySchedule.toAdjusted(businessDayAdjustment.resolve(refData));
    }
    return paySchedule;
  }
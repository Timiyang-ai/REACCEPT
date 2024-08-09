public Schedule createSchedule(Schedule accrualSchedule, ReferenceData refData) {
    // payment frequency of Term absorbs everything
    if (paymentFrequency.equals(Frequency.TERM)) {
      if (firstRegularStartDate != null &&
          !firstRegularStartDate.equals(accrualSchedule.getUnadjustedStartDate()) &&
          !firstRegularStartDate.equals(accrualSchedule.getStartDate())) {
        throw new ScheduleException("Unable to create schedule for frequency 'Term' when firstRegularStartDate != startDate");
      }
      if (lastRegularEndDate != null &&
          !lastRegularEndDate.equals(accrualSchedule.getUnadjustedEndDate()) &&
          !lastRegularEndDate.equals(accrualSchedule.getEndDate())) {
        throw new ScheduleException("Unable to create schedule for frequency 'Term' when lastRegularEndDate != endDate");
      }
      return accrualSchedule.mergeToTerm();
    }
    // derive schedule, retaining stubs as payment periods
    int accrualPeriodsPerPayment = paymentFrequency.exactDivide(accrualSchedule.getFrequency());
    Schedule paySchedule;
    if (firstRegularStartDate != null && lastRegularEndDate != null) {
      paySchedule = accrualSchedule.merge(accrualPeriodsPerPayment, firstRegularStartDate, lastRegularEndDate);

    } else if (firstRegularStartDate != null || lastRegularEndDate != null) {
      LocalDate firstRegular = firstRegularStartDate != null ?
          firstRegularStartDate :
          accrualSchedule.getInitialStub()
              .map(stub -> stub.getUnadjustedEndDate())
              .orElse(accrualSchedule.getUnadjustedStartDate());
      LocalDate lastRegular = lastRegularEndDate != null ?
          lastRegularEndDate :
          accrualSchedule.getFinalStub()
              .map(stub -> stub.getUnadjustedStartDate())
              .orElse(accrualSchedule.getUnadjustedEndDate());
      paySchedule = accrualSchedule.merge(
          accrualPeriodsPerPayment, 
          firstRegular,
          lastRegular);

    } else {
      boolean rollForwards = !accrualSchedule.getInitialStub().isPresent();
      paySchedule = accrualSchedule.mergeRegular(accrualPeriodsPerPayment, rollForwards);
    }
    // adjust for business days
    if (businessDayAdjustment != null) {
      return paySchedule.toAdjusted(businessDayAdjustment.resolve(refData));
    }
    return paySchedule;
  }
Schedule createSchedule(Schedule accrualSchedule) {
    int size = accrualSchedule.size();
    // Term or matching frequency means no work to do
    if (size == 1 || paymentFrequency.equals(accrualSchedule.getFrequency())) {
      return accrualSchedule;
    }
    // initial
    List<SchedulePeriod> paymentSchedule = new ArrayList<>();
    Optional<SchedulePeriod> initialStub = accrualSchedule.getInitialStub();
    if (initialStub.isPresent()) {
      paymentSchedule.add(initialStub.get());
    }
    // regular
    paymentSchedule.addAll(
        splitRegular(accrualSchedule.getRegularPeriods(), accrualSchedule.getFrequency(), initialStub.isPresent()));
    // final 
    Optional<SchedulePeriod> finalStub = accrualSchedule.getFinalStub();
    if (finalStub.isPresent()) {
      paymentSchedule.add(finalStub.get());
    }
    return Schedule.of(paymentSchedule);
  }
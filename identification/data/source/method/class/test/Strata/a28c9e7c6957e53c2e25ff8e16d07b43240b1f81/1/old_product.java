Schedule createSchedule(Schedule accrualSchedule) {
    int size = accrualSchedule.size();
    // Term or matching frequency means no work to do
    if (size == 1 || paymentFrequency.equals(accrualSchedule.getFrequency())) {
      return accrualSchedule;
    }
    // payment frequency of Term absorbs everything
    if (paymentFrequency.equals(Frequency.TERM)) {
      return accrualSchedule.mergeToTerm();
    }
    // derive schedule, retaining stubs as payment periods
    Optional<SchedulePeriod> initialStub = accrualSchedule.getInitialStub();
    Optional<SchedulePeriod> finalStub = accrualSchedule.getFinalStub();
    List<SchedulePeriod> paymentSchedule = new ArrayList<>();
    if (initialStub.isPresent()) {
      paymentSchedule.add(initialStub.get());
    }
    paymentSchedule.addAll(splitRegular(accrualSchedule));
    if (finalStub.isPresent()) {
      paymentSchedule.add(finalStub.get());
    }
    return Schedule.of(paymentSchedule);
  }
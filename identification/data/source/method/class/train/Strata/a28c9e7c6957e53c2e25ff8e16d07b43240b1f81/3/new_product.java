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
    Optional<SchedulePeriod> initialStub = accrualSchedule.getInitialStub();
    Optional<SchedulePeriod> finalStub = accrualSchedule.getFinalStub();
    // initial
    List<SchedulePeriod> paymentSchedule = new ArrayList<>();
    if (initialStub.isPresent()) {
      paymentSchedule.add(initialStub.get());
    }
    // regular
    paymentSchedule.addAll(splitRegular(accrualSchedule));
    // final 
    if (finalStub.isPresent()) {
      paymentSchedule.add(finalStub.get());
    }
    return Schedule.of(paymentSchedule);
  }
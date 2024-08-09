public Schedule merge(int groupSize, LocalDate firstRegularStartDate, LocalDate lastRegularEndDate) {
    ArgChecker.notNegativeOrZero(groupSize, "groupSize");
    ArgChecker.inOrderOrEqual(firstRegularStartDate, lastRegularEndDate, "firstRegularStartDate", "lastRegularEndDate");
    if (isTerm() || groupSize == 1) {
      return this;
    }
    // determine stubs and regular
    int startRegularIndex = -1;
    int endRegularIndex = -1;
    for (int i = 0; i < size(); i++) {
      SchedulePeriod period = periods.get(i);
      if (period.getUnadjustedStartDate().equals(firstRegularStartDate) || period.getStartDate().equals(firstRegularStartDate)) {
        startRegularIndex = i;
      }
      if (period.getUnadjustedEndDate().equals(lastRegularEndDate) || period.getEndDate().equals(lastRegularEndDate)) {
        endRegularIndex = i + 1;
      }
    }
    if (startRegularIndex < 0) {
      throw new ScheduleException(
          "Unable to merge schedule, firstRegularStartDate {} does not match any date in the underlying schedule {}",
          firstRegularStartDate,
          getUnadjustedDates());
    }
    if (endRegularIndex < 0) {
      throw new ScheduleException(
          "Unable to merge schedule, lastRegularEndDate {} does not match any date in the underlying schedule {}",
          lastRegularEndDate,
          getUnadjustedDates());
    }
    int numberRegular = endRegularIndex - startRegularIndex;
    if ((numberRegular % groupSize) != 0) {
      Period newFrequency = frequency.getPeriod().multipliedBy(groupSize);
      throw new ScheduleException(
          "Unable to merge schedule, firstRegularStartDate {} and lastRegularEndDate {} cannot be used to " +
              "create regular periods of frequency '{}'",
          firstRegularStartDate, lastRegularEndDate, newFrequency);
    }
    List<SchedulePeriod> newSchedule = new ArrayList<>();
    if (startRegularIndex > 0) {
      newSchedule.add(createSchedulePeriod(periods.subList(0, startRegularIndex)));
    }
    for (int i = startRegularIndex; i < endRegularIndex; i += groupSize) {
      newSchedule.add(createSchedulePeriod(periods.subList(i, i + groupSize)));
    }
    if (endRegularIndex < periods.size()) {
      newSchedule.add(createSchedulePeriod(periods.subList(endRegularIndex, periods.size())));
    }
    // build schedule
    return Schedule.builder()
        .periods(newSchedule)
        .frequency(Frequency.of(frequency.getPeriod().multipliedBy(groupSize)))
        .rollConvention(rollConvention)
        .build();
  }
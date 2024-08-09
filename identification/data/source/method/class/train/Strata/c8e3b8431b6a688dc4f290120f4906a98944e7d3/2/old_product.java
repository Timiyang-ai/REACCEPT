public default double relativeYearFraction(LocalDate firstDate, LocalDate secondDate, ScheduleInfo scheduleInfo) {
    ArgChecker.notNull(firstDate, "firstDate");
    ArgChecker.notNull(secondDate, "secondDate");
    ArgChecker.notNull(scheduleInfo, "scheduleInfo");
    if (secondDate.isBefore(firstDate)) {
      return -yearFraction(secondDate, firstDate, scheduleInfo);
    }
    return yearFraction(firstDate, secondDate, scheduleInfo);
  }
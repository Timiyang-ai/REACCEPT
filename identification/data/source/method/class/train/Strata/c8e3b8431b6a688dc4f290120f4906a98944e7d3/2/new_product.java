public default double relativeYearFraction(LocalDate firstDate, LocalDate secondDate, ScheduleInfo scheduleInfo) {
    if (secondDate.isBefore(firstDate)) {
      return -yearFraction(secondDate, firstDate, scheduleInfo);
    }
    return yearFraction(firstDate, secondDate, scheduleInfo);
  }
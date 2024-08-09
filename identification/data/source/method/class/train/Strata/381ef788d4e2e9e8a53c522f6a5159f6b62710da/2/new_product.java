public double yearFraction(DayCount dayCount, Schedule schedule) {
    ArgChecker.notNull(dayCount, "dayCount");
    ArgChecker.notNull(schedule, "schedule");
    return dayCount.yearFraction(startDate, endDate, schedule);
  }
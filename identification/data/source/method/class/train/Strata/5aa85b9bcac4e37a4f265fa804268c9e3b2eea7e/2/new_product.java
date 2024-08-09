public default double yearFraction(LocalDate firstDate, LocalDate secondDate) {
    return yearFraction(firstDate, secondDate, DayCounts.SIMPLE_SCHEDULE_INFO);
  }
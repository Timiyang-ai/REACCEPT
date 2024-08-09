public RollConvention toRollConvention(
      LocalDate start,
      LocalDate end,
      Frequency frequency,
      boolean preferEndOfMonth) {

    ArgChecker.notNull(start, "start");
    ArgChecker.notNull(end, "end");
    ArgChecker.notNull(frequency, "frequency");
    // if the day-of-month differs, need to handle case where one or both
    // dates are at the end of the month, and in different months
    if (this == NONE && frequency.isMonthBased()) {
      if (start.getDayOfMonth() != end.getDayOfMonth() &&
          start.getLong(PROLEPTIC_MONTH) != end.getLong(PROLEPTIC_MONTH) &&
          (start.getDayOfMonth() == start.lengthOfMonth() || end.getDayOfMonth() == end.lengthOfMonth())) {
        
        return preferEndOfMonth ?  
            RollConventions.EOM : 
            RollConvention.ofDayOfMonth(Math.max(start.getDayOfMonth(), end.getDayOfMonth()));
      }
    }
    if (isCalculateBackwards()) {
      return impliedRollConvention(end, start, frequency, preferEndOfMonth);
    } else {
      return impliedRollConvention(start, end, frequency, preferEndOfMonth);
    }
  }
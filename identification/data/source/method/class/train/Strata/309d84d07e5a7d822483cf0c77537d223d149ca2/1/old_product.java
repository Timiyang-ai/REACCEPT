private static RollConvention toRollConvention(LocalDate date, Frequency frequency, boolean preferEndOfMonth) {
    if (frequency.isMonthBased()) {
      if (preferEndOfMonth && date.getDayOfMonth() == date.lengthOfMonth()) {
        return RollConventions.EOM;
      }
      return RollConvention.ofDayOfMonth(date.getDayOfMonth());
    } else if (frequency.isWeekBased()) {
      return RollConvention.ofDayOfWeek(date.getDayOfWeek());
    } else {
      // neither monthly nor weekly means no known roll convention
      return RollConventions.NONE;
    }
  }
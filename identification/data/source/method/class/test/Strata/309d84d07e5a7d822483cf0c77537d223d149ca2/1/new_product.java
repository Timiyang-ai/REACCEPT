public final RollConvention toRollConvention(
      LocalDate start, LocalDate end, Frequency frequency, boolean preferEndOfMonth) {
    ArgChecker.notNull(start, "start");
    ArgChecker.notNull(end, "end");
    ArgChecker.notNull(frequency, "frequency");
    if (isCalculateBackwards()) {
      return toRollConvention(end, frequency, preferEndOfMonth);
    } else {
      return toRollConvention(start, frequency, preferEndOfMonth);
    }
  }
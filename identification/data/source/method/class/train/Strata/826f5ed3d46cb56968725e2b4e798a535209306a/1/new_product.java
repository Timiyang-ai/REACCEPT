public static IborAveragedFixing ofDaysInResetPeriod(
      LocalDate fixingDate,
      LocalDate startDate,
      LocalDate endDate,
      Double fixedRate) {
    ArgChecker.notNull(fixingDate, "fixingDate");
    ArgChecker.notNull(startDate, "startDate");
    ArgChecker.notNull(endDate, "endDate");
    return IborAveragedFixing.builder()
        .fixingDate(fixingDate)
        .fixedRate(fixedRate)
        .weight(endDate.toEpochDay() - startDate.toEpochDay())
        .build();
  }
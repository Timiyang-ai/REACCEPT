public static IborAveragedFixing ofDaysInResetPeriod(LocalDate fixingDate, LocalDate startDate, LocalDate endDate) {
    return IborAveragedFixing.builder()
        .fixingDate(fixingDate)
        .weight(endDate.toEpochDay() - startDate.toEpochDay())
        .build();
  }
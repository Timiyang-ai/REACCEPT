public static IborAveragedFixing ofDaysInResetPeriod(LocalDate fixingDate, LocalDate startDate, LocalDate endDate) {
    return ofDaysInResetPeriod(fixingDate, null, startDate, endDate);
  }
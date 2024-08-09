public static IborAveragedFixing ofDaysInResetPeriod(
      IborRateObservation observation,
      LocalDate startDate,
      LocalDate endDate) {
    return ofDaysInResetPeriod(observation, startDate, endDate, null);
  }
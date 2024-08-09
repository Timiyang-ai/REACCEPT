public static IborAveragedFixing ofDaysInResetPeriod(
      IborIndexObservation observation,
      LocalDate startDate,
      LocalDate endDate) {
    return ofDaysInResetPeriod(observation, startDate, endDate, null);
  }
public static IborAveragedFixing ofDaysInResetPeriod(
      IborIndexObservation observation,
      LocalDate startDate,
      LocalDate endDate,
      Double fixedRate) {
    ArgChecker.notNull(observation, "observation");
    ArgChecker.notNull(startDate, "startDate");
    ArgChecker.notNull(endDate, "endDate");
    return IborAveragedFixing.builder()
        .observation(observation)
        .fixedRate(fixedRate)
        .weight(endDate.toEpochDay() - startDate.toEpochDay())
        .build();
  }
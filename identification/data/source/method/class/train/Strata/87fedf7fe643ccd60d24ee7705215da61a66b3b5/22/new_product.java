@Override
  public PointSensitivityBuilder periodRatePointSensitivity(
      OvernightIndexObservation startDateObservation,
      LocalDate endDate) {

    LocalDate startDate = startDateObservation.getEffectiveDate();
    ArgChecker.inOrderNotEqual(startDate, endDate, "startDate", "endDate");
    return OvernightRateSensitivity.ofPeriod(startDateObservation, endDate, 1d);
  }
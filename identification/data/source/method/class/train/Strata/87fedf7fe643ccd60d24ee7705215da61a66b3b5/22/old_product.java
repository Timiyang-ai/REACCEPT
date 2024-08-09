@Override
  public PointSensitivityBuilder periodRatePointSensitivity(
      OvernightIndexObservation startDateObservation,
      LocalDate endDate) {

    LocalDate startDate = startDateObservation.getEffectiveDate();
    ArgChecker.inOrderNotEqual(startDate, endDate, "startDate", "endDate");
    ArgChecker.inOrderOrEqual(getValuationDate(), startDate, "valuationDate", "startDate");
    return OvernightRateSensitivity.ofPeriod(startDateObservation, endDate, 1d);
  }
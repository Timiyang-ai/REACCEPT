@Override
  public PointSensitivityBuilder periodRatePointSensitivity(LocalDate startDate, LocalDate endDate) {
    ArgChecker.inOrderNotEqual(startDate, endDate, "startDate", "endDate");
    ArgChecker.inOrderOrEqual(getValuationDate(), startDate, "valuationDate", "startDate");
    return OvernightRateSensitivity.of(index, startDate, endDate, index.getCurrency(), 1d);
  }
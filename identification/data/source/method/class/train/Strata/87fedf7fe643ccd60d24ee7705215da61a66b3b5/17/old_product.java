@Override
  public PointSensitivityBuilder periodRatePointSensitivity(LocalDate startDate, LocalDate endDate) {
    ArgChecker.inOrderNotEqual(startDate, endDate, "startDate", "endDate");
    ArgChecker.inOrderOrEqual(getValuationDate(), startDate, "valuationDate", "startDate");
    return OvernightRateSensitivity.of(index, index.getCurrency(), startDate, endDate, 1d);
  }
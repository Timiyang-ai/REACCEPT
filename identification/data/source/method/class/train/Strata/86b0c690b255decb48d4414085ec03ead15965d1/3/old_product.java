@Override
  public double periodRate(LocalDate startDate, LocalDate endDate) {
    ArgChecker.inOrderNotEqual(startDate, endDate, "startDate", "endDate");
    ArgChecker.inOrderOrEqual(getValuationDate(), startDate, "valuationDate", "startDate");
    double fixingYearFraction = index.getDayCount().yearFraction(startDate, endDate);
    return simplyCompoundForwardRate(startDate, endDate, fixingYearFraction);
  }
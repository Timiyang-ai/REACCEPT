@Override
  public double periodRate(OvernightIndexObservation startDateObservation, LocalDate endDate) {
    LocalDate effectiveDate = startDateObservation.getEffectiveDate();
    ArgChecker.inOrderNotEqual(effectiveDate, endDate, "startDate", "endDate");
    ArgChecker.inOrderOrEqual(getValuationDate(), effectiveDate, "valuationDate", "startDate");
    double accrualFactor = startDateObservation.getIndex().getDayCount().yearFraction(effectiveDate, endDate);
    return simplyCompoundForwardRate(effectiveDate, endDate, accrualFactor);
  }
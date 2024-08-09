@Override
  public double periodRate(OvernightIndexObservation startDateObservation, LocalDate endDate) {
    LocalDate effectiveDate = startDateObservation.getEffectiveDate();
    ArgChecker.inOrderNotEqual(effectiveDate, endDate, "startDate", "endDate");
    double accrualFactor = startDateObservation.getIndex().getDayCount().yearFraction(effectiveDate, endDate);
    return simplyCompoundForwardRate(effectiveDate, endDate, accrualFactor);
  }
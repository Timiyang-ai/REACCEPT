@Override
  public PointSensitivityBuilder ratePointSensitivity(LocalDate fixingDate) {
    LocalDate valuationDate = getValuationDate();
    LocalDate publicationDate = index.calculatePublicationFromFixing(fixingDate);
    if (publicationDate.isBefore(valuationDate) ||
        (publicationDate.equals(valuationDate) && fixings.get(fixingDate).isPresent())) {
      return PointSensitivityBuilder.none();
    }
    LocalDate fixingEndDate = index.calculateMaturityFromFixing(fixingDate);
    return OvernightRateSensitivity.of(index, fixingDate, fixingEndDate, index.getCurrency(), 1d);
  }
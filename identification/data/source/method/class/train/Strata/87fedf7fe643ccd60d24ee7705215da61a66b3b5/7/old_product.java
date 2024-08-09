@Override
  public PointSensitivityBuilder ratePointSensitivity(LocalDate fixingDate) {
    LocalDate valuationDate = getValuationDate();
    LocalDate publicationDate = index.calculatePublicationFromFixing(fixingDate);
    if (publicationDate.isBefore(valuationDate) ||
        (publicationDate.equals(valuationDate) && timeSeries.get(fixingDate).isPresent())) {
      return PointSensitivityBuilder.none();
    }
    LocalDate fixingStartDate = index.calculateEffectiveFromFixing(fixingDate);
    LocalDate fixingEndDate = index.calculateMaturityFromEffective(fixingStartDate);
    return OvernightRateSensitivity.of(index, index.getCurrency(), fixingDate, fixingEndDate, 1d);
  }
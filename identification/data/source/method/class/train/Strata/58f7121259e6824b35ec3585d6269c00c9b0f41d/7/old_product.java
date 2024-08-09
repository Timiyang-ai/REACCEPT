@Override
  public CurveMetadata metadata(LocalDate valuationDate, ReferenceData refData) {
    return metadata(valuationDate, refData, ImmutableMap.of());
  }
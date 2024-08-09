@Override
  public CurveMetadata metadata(LocalDate valuationDate) {
    return metadata(valuationDate, ImmutableMap.of());
  }
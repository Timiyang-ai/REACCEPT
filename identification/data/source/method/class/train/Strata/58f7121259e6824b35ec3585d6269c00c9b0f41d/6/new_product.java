@Override
  public NodalCurve curve(
      LocalDate valuationDate,
      CurveMetadata metadata,
      DoubleArray parameters) {

    DoubleArray nodeTimes = buildNodeTimes(valuationDate, metadata);
    return InterpolatedNodalCurve.builder()
        .metadata(metadata)
        .xValues(nodeTimes)
        .yValues(parameters)
        .extrapolatorLeft(extrapolatorLeft)
        .interpolator(interpolator)
        .extrapolatorRight(extrapolatorRight).build();
  }
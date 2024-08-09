@Override
  public NodalCurve curve(LocalDate valuationDate, DoubleArray parameters, Map<CurveInfoType<?>, Object> additionalInfo) {
    CurveMetadata meta = metadata(valuationDate, additionalInfo);
    DoubleArray nodeTimes = DoubleArray.of(getParameterCount(), i -> {
      LocalDate nodeDate = ((DatedCurveParameterMetadata) meta.getParameterMetadata().get().get(i)).getDate();
      return getDayCount().get().yearFraction(valuationDate, nodeDate);
    });
    return InterpolatedNodalCurve.builder()
        .metadata(meta)
        .xValues(nodeTimes)
        .yValues(parameters)
        .extrapolatorLeft(extrapolatorLeft)
        .interpolator(interpolator)
        .extrapolatorRight(extrapolatorRight).build();
  }
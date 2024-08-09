@Override
  public NodalCurve curve(LocalDate valuationDate, DoubleMatrix1D parameters, Map<CurveInfoType<?>, Object> additionalInfo) {
    CurveMetadata meta = metadata(valuationDate, additionalInfo);
    DoubleMatrix1D nodeTimes = DoubleMatrix1D.of(getParameterCount(), i -> {
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
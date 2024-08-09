@Override
  public NodalCurve curve(
      LocalDate valuationDate,
      CurveMetadata metadata,
      DoubleArray parameters) {

    DoubleArray nodeTimes = DoubleArray.of(getParameterCount(), i -> {
      LocalDate nodeDate = ((DatedCurveParameterMetadata) metadata.getParameterMetadata().get().get(i)).getDate();
      return getDayCount().get().yearFraction(valuationDate, nodeDate);
    });
    return InterpolatedNodalCurve.builder()
        .metadata(metadata)
        .xValues(nodeTimes)
        .yValues(parameters)
        .extrapolatorLeft(extrapolatorLeft)
        .interpolator(interpolator)
        .extrapolatorRight(extrapolatorRight).build();
  }
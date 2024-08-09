@Override
  public NodalCurve curve(
      LocalDate valuationDate,
      CurveMetadata metadata,
      DoubleArray parameters,
      Map<CurveInfoType<?>, Object> additionalInfo) {

    CurveMetadata combinedMetadata = metadata.withInfo(additionalInfo);
    DoubleArray nodeTimes = DoubleArray.of(getParameterCount(), i -> {
      LocalDate nodeDate = ((DatedCurveParameterMetadata) combinedMetadata.getParameterMetadata().get().get(i)).getDate();
      return getDayCount().get().yearFraction(valuationDate, nodeDate);
    });
    return InterpolatedNodalCurve.builder()
        .metadata(combinedMetadata)
        .xValues(nodeTimes)
        .yValues(parameters)
        .extrapolatorLeft(extrapolatorLeft)
        .interpolator(interpolator)
        .extrapolatorRight(extrapolatorRight).build();
  }
@Override
  public NodalCurve curve(LocalDate valuationDate, double[] parameters, CurveCalibrationInfo calibrationInfo) {
    CurveMetadata meta = metadata(valuationDate, calibrationInfo);
    double[] nodeTimes = new double[getParameterCount()];
    for (int i = 0; i < getParameterCount(); i++) {
      LocalDate nodeDate = ((DatedCurveParameterMetadata) meta.getParameterMetadata().get().get(i)).getDate();
      nodeTimes[i] = getDayCount().get().yearFraction(valuationDate, nodeDate);
    }
    return InterpolatedNodalCurve.builder()
        .metadata(meta)
        .xValues(nodeTimes)
        .yValues(parameters)
        .extrapolatorLeft(extrapolatorLeft)
        .interpolator(interpolator)
        .extrapolatorRight(extrapolatorRight).build();
  }
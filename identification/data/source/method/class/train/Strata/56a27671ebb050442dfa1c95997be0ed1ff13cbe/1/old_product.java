@Override
  public InterpolatedNodalCurve withMetadata(CurveMetadata metadata) {
    return new InterpolatedNodalCurve(metadata, xValues, yValues, extrapolatorLeft, interpolator, extrapolatorRight);
  }
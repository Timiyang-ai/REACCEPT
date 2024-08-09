public InterpolatedNodalCurve withNode(int index, double x, double y) {
    DoubleArray xExtended = xValues.subArray(0, index).concat(new double[] {x}).concat(xValues.subArray(index));
    DoubleArray yExtended = yValues.subArray(0, index).concat(new double[] {y}).concat(yValues.subArray(index));
    CurveMetadata metadata = getMetadata().withParameterMetadata(null);
    return new InterpolatedNodalCurve(metadata, xExtended, yExtended, extrapolatorLeft, interpolator, extrapolatorRight);
  }
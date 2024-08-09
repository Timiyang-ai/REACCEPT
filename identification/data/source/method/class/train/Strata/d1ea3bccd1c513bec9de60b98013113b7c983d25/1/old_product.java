public InterpolatedNodalCurve withNode(int index, double x, double y) {
    double[] xExtended = Arrays.copyOf(xValues, xValues.length + 1);
    double[] yExtended = Arrays.copyOf(yValues, yValues.length + 1);
    System.arraycopy(xExtended, index, xExtended, index + 1, xValues.length - index);
    System.arraycopy(yExtended, index, yExtended, index + 1, yValues.length - index);
    xExtended[index] = x;
    yExtended[index] = y;
    return new InterpolatedNodalCurve(
        CurveMetadata.of(getName()), xExtended, yExtended, extrapolatorLeft, interpolator, extrapolatorRight);
  }
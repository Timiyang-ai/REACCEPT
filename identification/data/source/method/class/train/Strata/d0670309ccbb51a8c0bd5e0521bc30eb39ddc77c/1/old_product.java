@Override
  public InterpolatedNodalSurface withZValues(double[] zValues) {
    return new InterpolatedNodalSurface(metadata, xValues, yValues, zValues, interpolator);
  }
@Override
  public InterpolatedNodalSurface withZValues(DoubleArray zValues) {
    return new InterpolatedNodalSurface(metadata, xValues, yValues, zValues, interpolator);
  }
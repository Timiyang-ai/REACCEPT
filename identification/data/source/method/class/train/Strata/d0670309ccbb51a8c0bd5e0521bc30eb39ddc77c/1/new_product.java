@Override
  public InterpolatedNodalSurface withZValues(DoubleMatrix1D zValues) {
    return new InterpolatedNodalSurface(metadata, xValues, yValues, zValues, interpolator);
  }
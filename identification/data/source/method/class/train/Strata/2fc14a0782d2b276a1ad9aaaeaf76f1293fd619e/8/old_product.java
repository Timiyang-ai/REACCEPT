public DoubleMatrix1D inverseTransform(final DoubleMatrix1D modelParms) {
    return new DoubleMatrix1D(inverseTransform(modelParms.getData()));
  }
public DoubleMatrix1D inverseTransform(DoubleMatrix1D modelParms) {
    return new DoubleMatrix1D(inverseTransform(modelParms.getData()));
  }
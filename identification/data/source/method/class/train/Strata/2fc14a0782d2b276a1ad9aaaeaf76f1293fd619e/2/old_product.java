public DoubleMatrix1D inverseTransform(DoubleMatrix1D modelParms) {
    return DoubleMatrix1D.copyOf(inverseTransform(modelParms.toArray()));
  }
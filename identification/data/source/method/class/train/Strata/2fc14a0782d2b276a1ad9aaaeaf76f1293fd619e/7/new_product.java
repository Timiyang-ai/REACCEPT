public DoubleMatrix jacobian(DoubleArray fitParms) {
    return DoubleMatrix.copyOf(jacobian(fitParms.toArray()));
  }
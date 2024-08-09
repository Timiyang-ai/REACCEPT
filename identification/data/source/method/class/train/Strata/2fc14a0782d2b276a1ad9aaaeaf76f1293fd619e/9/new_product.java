public DoubleMatrix2D jacobian(DoubleMatrix1D fitParms) {
    return DoubleMatrix2D.copyOf(jacobian(fitParms.toArray()));
  }
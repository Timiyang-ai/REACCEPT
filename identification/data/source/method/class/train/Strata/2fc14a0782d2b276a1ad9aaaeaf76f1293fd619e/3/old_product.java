public DoubleMatrix2D jacobian(final DoubleMatrix1D fitParms) {
    return new DoubleMatrix2D(jacobian(fitParms.getData()));
  }
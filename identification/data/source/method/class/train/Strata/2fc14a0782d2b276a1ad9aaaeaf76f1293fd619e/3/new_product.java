public DoubleMatrix2D jacobian(DoubleMatrix1D fitParms) {
    return new DoubleMatrix2D(jacobian(fitParms.getData()));
  }
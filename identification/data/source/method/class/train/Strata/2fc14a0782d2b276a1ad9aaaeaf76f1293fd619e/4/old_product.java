public DoubleMatrix1D transform(final DoubleMatrix1D fitParms) {
    return new DoubleMatrix1D(transform(fitParms.getData()));
  }
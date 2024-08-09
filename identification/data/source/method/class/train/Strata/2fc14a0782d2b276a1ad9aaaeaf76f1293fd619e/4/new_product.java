public DoubleMatrix1D transform(DoubleMatrix1D fitParms) {
    return new DoubleMatrix1D(transform(fitParms.getData()));
  }
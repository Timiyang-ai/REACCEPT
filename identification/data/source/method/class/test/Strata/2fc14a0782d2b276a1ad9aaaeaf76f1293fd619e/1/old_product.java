public DoubleMatrix1D transform(DoubleMatrix1D fitParms) {
    return DoubleMatrix1D.copyOf(transform(fitParms.toArray()));
  }
public DoubleArray transform(DoubleArray fitParms) {
    return DoubleArray.copyOf(transform(fitParms.toArray()));
  }
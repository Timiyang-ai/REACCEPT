public DoubleArray concat(double[] arrayToConcat) {
    if (array.length == 0) {
      return copyOf(arrayToConcat);
    }
    if (arrayToConcat.length == 0) {
      return this;
    }
    double[] result = new double[array.length + arrayToConcat.length];
    System.arraycopy(array, 0, result, 0, array.length);
    System.arraycopy(arrayToConcat, 0, result, array.length, arrayToConcat.length);
    return new DoubleArray(result);
  }
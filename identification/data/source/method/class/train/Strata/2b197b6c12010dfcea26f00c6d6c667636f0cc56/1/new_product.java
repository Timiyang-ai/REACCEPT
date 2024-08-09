public IntArray concat(int... arrayToConcat) {
    if (array.length == 0) {
      return copyOf(arrayToConcat);
    }
    if (arrayToConcat.length == 0) {
      return this;
    }
    int[] result = new int[array.length + arrayToConcat.length];
    System.arraycopy(array, 0, result, 0, array.length);
    System.arraycopy(arrayToConcat, 0, result, array.length, arrayToConcat.length);
    return new IntArray(result);
  }
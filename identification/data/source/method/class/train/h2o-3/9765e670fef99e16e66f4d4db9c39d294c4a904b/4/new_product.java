public TestFrameBuilder withRandomIntDataForCol(int column, int size, int min, int max, long seed) {
    assert max > min;
    assert seed + size * size <= Long.MAX_VALUE;
    double[] arr = new double[size];
    for(int i = 0; i < size; i++) {
      arr[i] = min + new Random(seed + i * size).nextInt(max - min);
    }
    numericData.put(column, arr);
    return this;
  }
public TestFrameBuilder withRandomDoubleDataForCol(int column, int size, int min, int max, long seed) {
    assert max >= min;
    double[] arr = new double[size];
    for(int i = 0; i < size; i++) {
      arr[i] = min + (max - min) * new Random(seed + i * size).nextDouble();
    }
    numericData.put(column, arr);
    return this;
  }
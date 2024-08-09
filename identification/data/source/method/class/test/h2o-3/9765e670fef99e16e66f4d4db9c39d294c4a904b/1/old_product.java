public TestFrameBuilder withRandomIntDataForCol(int column, int size, int min, int max) {
    assert max > min;
    double[] arr = new double[size];
    for(int i = 0; i < size; i++) {
      arr[i] = min + new Random().nextInt(max - min);
    }
    numericData.put(column, arr);
    return this;
  }
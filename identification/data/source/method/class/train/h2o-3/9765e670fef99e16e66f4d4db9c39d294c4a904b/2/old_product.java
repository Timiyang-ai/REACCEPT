public TestFrameBuilder withRandomBinaryDataForCol(int column, int size, long seed) {
    String[] arr = new String[size];
    for(int i = 0; i < size; i++) {
      arr[i] = Boolean.toString( new Random(seed).nextBoolean());
    }
    stringData.put(column, arr);
    return this;
  }
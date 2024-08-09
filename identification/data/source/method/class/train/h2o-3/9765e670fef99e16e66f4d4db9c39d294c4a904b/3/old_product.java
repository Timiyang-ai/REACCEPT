public TestFrameBuilder withRandomBinaryDataForCol(int column, int size) {
    String[] arr = new String[size];
    for(int i = 0; i < size; i++) {
      arr[i] = Boolean.toString( new Random().nextBoolean());
    }
    stringData.put(column, arr);
    return this;
  }
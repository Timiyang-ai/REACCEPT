public TestFrameBuilder withRandomBinaryDataForCol(int column, int size, long seed) {
    String[] arr = new String[size];
    Random generator = new Random();
    long multiplierFromRandomClass = 0x5DEECE66DL;
    assert seed + size * multiplierFromRandomClass < Long.MAX_VALUE;
    for(int i = 0; i < size; i++) {
      generator.setSeed(seed + i * multiplierFromRandomClass);
      arr[i] = Boolean.toString( generator.nextBoolean());
    }
    stringData.put(column, arr);
    return this;
  }
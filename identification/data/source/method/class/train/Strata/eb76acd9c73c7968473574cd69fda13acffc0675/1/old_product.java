public static Double[] toObject(double[] array) {
    if (array.length == 0) {
      return EMPTY_DOUBLE_OBJECT_ARRAY;
    }
    Double[] result = new Double[array.length];
    for (int i = 0; i < array.length; i++) {
      result[i] = new Double(array[i]);
    }
    return result;
  }
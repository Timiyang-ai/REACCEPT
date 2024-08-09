public static double[] combine(double[] array1, double[] array2, DoubleBinaryOperator operator) {
    int len1 = array1.length;
    int len2 = array2.length;
    if (len1 != len2) {
      throw new IllegalArgumentException("Arrays cannot be combined as they differ in length");
    }
    double[] result = new double[len1];
    for (int i = 0; i < len1; i++) {
      result[i] = operator.applyAsDouble(array1[i], array2[i]);
    }
    return result;
  }
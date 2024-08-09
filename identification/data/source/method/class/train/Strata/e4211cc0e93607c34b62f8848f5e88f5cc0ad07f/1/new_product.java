public static double[] combine(double[] array1, double[] array2, DoubleBinaryOperator operator) {
    int length = length(array1, array2);
    double[] result = new double[length];
    for (int i = 0; i < length; i++) {
      result[i] = operator.applyAsDouble(array1[i], array2[i]);
    }
    return result;
  }
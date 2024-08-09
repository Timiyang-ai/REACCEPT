public double combineReduce(DoubleArray other, DoubleTernaryOperator operator) {
    if (array.length != other.array.length) {
      throw new IllegalArgumentException("Arrays have different sizes");
    }
    double result = 0;
    for (int i = 0; i < array.length; i++) {
      result = operator.applyAsDouble(result, array[i], other.array[i]);
    }
    return result;
  }
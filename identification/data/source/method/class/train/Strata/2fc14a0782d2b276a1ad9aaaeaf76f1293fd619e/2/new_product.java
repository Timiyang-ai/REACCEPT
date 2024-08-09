public DoubleArray inverseTransform(DoubleArray modelParms) {
    return DoubleArray.copyOf(inverseTransform(modelParms.toArray()));
  }
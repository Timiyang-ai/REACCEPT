public static DoublesPair ofPair(Pair<Double, Double> pair) {
    ArgChecker.notNull(pair, "pair");
    return new DoublesPair(pair.getFirst(), pair.getSecond());
  }
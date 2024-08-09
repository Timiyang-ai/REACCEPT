public static DoublesPair ofPair(Pair<Double, Double> pair) {
    ArgChecker.notNull(pair, "pair");
    ArgChecker.notNull(pair.getFirst(), "pair.first");
    ArgChecker.notNull(pair.getSecond(), "pair.second");
    return new DoublesPair(pair.getFirst(), pair.getSecond());
  }
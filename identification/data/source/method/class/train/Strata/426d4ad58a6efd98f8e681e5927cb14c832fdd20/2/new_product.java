public static IntDoublePair ofPair(Pair<Integer, Double> pair) {
    ArgChecker.notNull(pair, "pair");
    return new IntDoublePair(pair.getFirst(), pair.getSecond());
  }
public static IntDoublePair ofPair(Pair<Integer, Double> pair) {
    ArgChecker.notNull(pair, "pair");
    ArgChecker.notNull(pair.getFirst(), "pair.first");
    ArgChecker.notNull(pair.getSecond(), "pair.second");
    return new IntDoublePair(pair.getFirst(), pair.getSecond());
  }
public static LongDoublePair ofPair(Pair<Long, Double> pair) {
    ArgChecker.notNull(pair, "pair");
    ArgChecker.notNull(pair.getFirst(), "pair.first");
    ArgChecker.notNull(pair.getSecond(), "pair.second");
    return new LongDoublePair(pair.getFirst(), pair.getSecond());
  }
public static LongDoublePair ofPair(Pair<Long, Double> pair) {
    ArgChecker.notNull(pair, "pair");
    return new LongDoublePair(pair.getFirst(), pair.getSecond());
  }
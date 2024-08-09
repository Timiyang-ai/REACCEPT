public static SortedMap<Integer, BitSet> closure(
      SortedMap<Integer, BitSet> equivalence) {
    final Closure closure = new Closure(equivalence);
    return closure.closure;
  }
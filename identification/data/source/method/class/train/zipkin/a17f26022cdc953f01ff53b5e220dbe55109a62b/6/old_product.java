public static List<DependencyLink> merge(Iterable<DependencyLink> in) {
    Map<Pair, Long> callCounts = new LinkedHashMap<>();
    Map<Pair, Long> errorCounts = new LinkedHashMap<>();

    for (DependencyLink link : in) {
      Pair parentChild = Pair.of(link.parent(), link.child());
      long callCount = callCounts.containsKey(parentChild) ? callCounts.get(parentChild) : 0L;
      callCount += link.callCount();
      callCounts.put(parentChild, callCount);
      long errorCount = errorCounts.containsKey(parentChild) ? errorCounts.get(parentChild) : 0L;
      errorCount += link.errorCount();
      errorCounts.put(parentChild, errorCount);
    }

    return link(callCounts, errorCounts);
  }
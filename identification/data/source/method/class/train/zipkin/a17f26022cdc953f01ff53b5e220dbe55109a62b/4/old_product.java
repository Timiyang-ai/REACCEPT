public static List<DependencyLink> merge(Iterable<DependencyLink> in) {
    Map<Pair<String>, Long> callCounts = new LinkedHashMap<>();
    Map<Pair<String>, Long> errorCounts = new LinkedHashMap<>();

    for (DependencyLink link : in) {
      Pair<String> parentChild = Pair.create(link.parent, link.child);
      long callCount = callCounts.containsKey(parentChild) ? callCounts.get(parentChild) : 0L;
      callCount += link.callCount;
      callCounts.put(parentChild, callCount);
      long errorCount = errorCounts.containsKey(parentChild) ? errorCounts.get(parentChild) : 0L;
      errorCount += link.errorCount;
      errorCounts.put(parentChild, errorCount);
    }

    return link(callCounts, errorCounts);
  }
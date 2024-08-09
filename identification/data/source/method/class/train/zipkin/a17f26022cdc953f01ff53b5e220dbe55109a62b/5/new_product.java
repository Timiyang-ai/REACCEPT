public static List<DependencyLink> merge(Iterable<DependencyLink> in) {
    Map<Pair, Long> callCounts = new LinkedHashMap<>();
    Map<Pair, Long> errorCounts = new LinkedHashMap<>();
    Map<Pair, Set<String>> callTraceIds = new LinkedHashMap<>();
    Map<Pair, Set<String>> errorTraceIds = new LinkedHashMap<>();

    for (DependencyLink link : in) {
      Pair parentChild = new Pair(link.parent(), link.child());
      long callCount = callCounts.containsKey(parentChild) ? callCounts.get(parentChild) : 0L;
      callCount += link.callCount();
      callCounts.put(parentChild, callCount);
      for (int i = 0, length = link.callTraceIds().size(); i < length; i++) {
        add(callTraceIds, parentChild, link.callTraceIds().get(i));
      }
      long errorCount = errorCounts.containsKey(parentChild) ? errorCounts.get(parentChild) : 0L;
      errorCount += link.errorCount();
      errorCounts.put(parentChild, errorCount);
      for (int i = 0, length = link.errorTraceIds().size(); i < length; i++) {
        add(callTraceIds, parentChild, link.errorTraceIds().get(i));
      }
    }

    return link(callCounts, errorCounts, callTraceIds, errorTraceIds);
  }
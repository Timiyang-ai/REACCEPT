public static List<DependencyLink> merge(Iterable<DependencyLink> in) {
    Map<Pair<String>, Long> links = new LinkedHashMap<Pair<String>, Long>();

    for (DependencyLink link : in) {
      Pair<String> parentChild = Pair.create(link.parent, link.child);
      long callCount = links.containsKey(parentChild) ? links.get(parentChild) : 0L;
      callCount += link.callCount;
      links.put(parentChild, callCount);
    }

    List<DependencyLink> result = new ArrayList<DependencyLink>(links.size());
    for (Map.Entry<Pair<String>, Long> link : links.entrySet()) {
      result.add(DependencyLink.create(link.getKey()._1, link.getKey()._2, link.getValue()));
    }
    return result;
  }
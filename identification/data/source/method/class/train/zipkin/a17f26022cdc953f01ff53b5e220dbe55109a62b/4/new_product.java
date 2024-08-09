public static List<DependencyLink> merge(Iterable<DependencyLink> in) {
    return toLinks(zipkin.internal.v2.internal.DependencyLinker.merge(fromLinks(in)));
  }
public static List<DependencyLink> merge(Iterable<DependencyLink> in) {
    return toLinks(zipkin2.internal.DependencyLinker.merge(fromLinks(in)));
  }
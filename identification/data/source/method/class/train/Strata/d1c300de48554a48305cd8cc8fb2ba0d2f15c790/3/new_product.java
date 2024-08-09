public ValueWithFailures<List<Position>> load(Collection<ResourceLocator> resources) {
    Collection<CharSource> charSources = resources.stream()
        .map(r -> r.getByteSource().asCharSourceUtf8UsingBom())
        .collect(toList());
    return parse(charSources);
  }
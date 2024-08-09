public ValueWithFailures<List<Trade>> load(Collection<ResourceLocator> resources) {
    Collection<CharSource> charSources = resources.stream()
        .map(r -> r.getByteSource().asCharSourceUtf8UsingBom())
        .collect(toList());
    return parse(charSources);
  }
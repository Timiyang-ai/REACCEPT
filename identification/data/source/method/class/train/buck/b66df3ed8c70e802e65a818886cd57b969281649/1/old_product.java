public void addEntry(
      FileHashCache fileHashCache,
      RuleKey key,
      SourcePathResolver resolver,
      ImmutableSet<SourcePath> universe,
      ImmutableSet<SourcePath> inputs)
      throws IOException {
    int index = 0;
    int[] hashIndices = new int[inputs.size()];
    ImmutableListMultimap<String, SourcePath> sortedUniverse =
        Multimaps.index(universe, sourcePathToManifestHeaderFunction(resolver));
    for (SourcePath input : inputs) {
      String relativePath = sourcePathToManifestHeader(input, resolver);
      ImmutableList<SourcePath> paths = sortedUniverse.get(relativePath);
      Preconditions.checkState(!paths.isEmpty());
      hashIndices[index++] =
          addHash(relativePath, hashSourcePathGroup(fileHashCache, resolver, paths));
    }
    entries.add(new Pair<>(key, hashIndices));
  }
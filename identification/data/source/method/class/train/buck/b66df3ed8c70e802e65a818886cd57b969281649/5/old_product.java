public void addEntry(
      FileHashCache fileHashCache,
      RuleKey key,
      SourcePathResolver resolver,
      ImmutableSet<SourcePath> universe,
      ImmutableSet<SourcePath> inputs)
      throws IOException {
    int index = 0;
    int[] hashIndices = new int[inputs.size()];
    ImmutableListMultimap<Path, SourcePath> sortedUniverse =
        Multimaps.index(
            universe,
            resolver.getRelativePathFunction());
    for (SourcePath input : inputs) {
      Path relativePath = resolver.getRelativePath(input);
      ImmutableList<SourcePath> paths = sortedUniverse.get(relativePath);
      Preconditions.checkState(!paths.isEmpty());
      hashIndices[index++] =
          addHash(
              relativePath.toString(),
              hashSourcePathGroup(fileHashCache, resolver, paths));
    }
    entries.add(new Pair<>(key, hashIndices));
  }
public void addEntry(
      FileHashLoader fileHashLoader,
      RuleKey key,
      SourcePathResolver resolver,
      ImmutableSet<SourcePath> universe,
      ImmutableSet<SourcePath> inputs)
      throws IOException {

    // Construct the input sub-paths that we care about.
    ImmutableSet<Object> inputPaths =
        RichStream.from(inputs)
            .map(path -> sourcePathToManifestPathKey(path, resolver))
            .toImmutableSet();

    // Create a multimap from paths we care about to SourcePaths that maps to them.
    ImmutableListMultimap<Object, SourcePath> sortedUniverse =
        index(universe, path -> sourcePathToManifestPathKey(path, resolver), inputPaths::contains);

    // Record the Entry.
    int index = 0;
    int[] hashIndices = new int[inputs.size()];
    for (Object relativePath : inputPaths) {
      ImmutableList<SourcePath> paths = sortedUniverse.get(relativePath);
      Preconditions.checkState(!paths.isEmpty());
      hashIndices[index++] =
          addHash(relativePath.toString(), hashSourcePathGroup(fileHashLoader, resolver, paths));
    }
    entries.add(new Pair<>(key, hashIndices));
  }
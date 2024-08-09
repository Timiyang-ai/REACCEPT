public void addEntry(
      FileHashLoader fileHashLoader,
      RuleKey key,
      SourcePathResolver resolver,
      ImmutableSet<SourcePath> universe,
      ImmutableSet<SourcePath> inputs)
      throws IOException {

    // Construct the input sub-paths that we care about.
    ImmutableSet<String> inputPaths =
        RichStream.from(inputs).map(sourcePathToManifestHeaderFunction(resolver)).toImmutableSet();

    // Create a multimap from paths we care about to SourcePaths that maps to them.
    ImmutableListMultimap<String, SourcePath> sortedUniverse =
        index(universe, sourcePathToManifestHeaderFunction(resolver), inputPaths::contains);

    // Record the Entry.
    int index = 0;
    int[] hashIndices = new int[inputs.size()];
    for (String relativePath : inputPaths) {
      ImmutableList<SourcePath> paths = sortedUniverse.get(relativePath);
      Preconditions.checkState(!paths.isEmpty());
      hashIndices[index++] =
          addHash(relativePath, hashSourcePathGroup(fileHashLoader, resolver, paths));
    }
    entries.add(new Pair<>(key, hashIndices));
  }
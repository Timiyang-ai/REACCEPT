@Override
  @Nullable
  public Path decompress(DecompressorDescriptor descriptor)
      throws IOException, InterruptedException {
    Path destinationDirectory = descriptor.repositoryPath();
    Optional<String> prefix = descriptor.prefix();
    boolean foundPrefix = false;
    // Store link, target info of symlinks, we create them after regular files are extracted.
    Map<Path, PathFragment> symlinks = new HashMap<>();

    try (ZipReader reader = new ZipReader(descriptor.archivePath().getPathFile())) {
      Collection<ZipFileEntry> entries = reader.entries();
      for (ZipFileEntry entry : entries) {
        StripPrefixedPath entryPath = StripPrefixedPath.maybeDeprefix(entry.getName(), prefix);
        foundPrefix = foundPrefix || entryPath.foundPrefix();
        if (entryPath.skip()) {
          continue;
        }
        extractZipEntry(
            reader, entry, destinationDirectory, entryPath.getPathFragment(), prefix, symlinks);
      }

      if (prefix.isPresent() && !foundPrefix) {
        Set<String> prefixes = new HashSet<>();
        for (ZipFileEntry entry : entries) {
          StripPrefixedPath entryPath =
              StripPrefixedPath.maybeDeprefix(entry.getName(), Optional.absent());
          Optional<String> suggestion =
              CouldNotFindPrefixException.maybeMakePrefixSuggestion(entryPath.getPathFragment());
          if (suggestion.isPresent()) {
            prefixes.add(suggestion.get());
          }
        }
        throw new CouldNotFindPrefixException(prefix.get(), prefixes);
      }
    }

    for (Map.Entry<Path, PathFragment> symlink : symlinks.entrySet()) {
      FileSystemUtils.ensureSymbolicLink(symlink.getKey(), symlink.getValue());
    }

    return destinationDirectory;
  }
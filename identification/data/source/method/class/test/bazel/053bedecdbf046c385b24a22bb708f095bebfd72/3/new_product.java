@Override
  @Nullable
  public Path decompress(DecompressorDescriptor descriptor) throws IOException {
    Path destinationDirectory = descriptor.archivePath().getParentDirectory();
    Optional<String> prefix = descriptor.prefix();
    boolean foundPrefix = false;
    try (ZipReader reader = new ZipReader(descriptor.archivePath().getPathFile())) {
      Collection<ZipFileEntry> entries = reader.entries();
      // Store link, target info of symlinks, we create them after regular files are extracted.
      Map<Path, PathFragment> symlinks = new HashMap<>();
      for (ZipFileEntry entry : entries) {
        StripPrefixedPath entryPath = StripPrefixedPath.maybeDeprefix(entry.getName(), prefix);
        foundPrefix = foundPrefix || entryPath.foundPrefix();
        if (entryPath.skip()) {
          continue;
        }
        extractZipEntry(reader, entry, destinationDirectory, entryPath.getPathFragment(), symlinks);
      }
      for (Map.Entry<Path, PathFragment> symlink : symlinks.entrySet()) {
        symlink.getKey().createSymbolicLink(symlink.getValue());
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

    return destinationDirectory;
  }
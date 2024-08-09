@Override
  @Nullable
  public Path decompress(DecompressorDescriptor descriptor) throws RepositoryFunctionException {
    Path destinationDirectory = descriptor.archivePath().getParentDirectory();
    Optional<String> prefix = descriptor.prefix();
    boolean foundPrefix = false;
    try (ZipReader reader = new ZipReader(descriptor.archivePath().getPathFile())) {
      Collection<ZipFileEntry> entries = reader.entries();
      for (ZipFileEntry entry : entries) {
        StripPrefixedPath entryPath = StripPrefixedPath.maybeDeprefix(entry.getName(), prefix);
        foundPrefix = foundPrefix || entryPath.foundPrefix();
        if (entryPath.skip()) {
          continue;
        }
        extractZipEntry(reader, entry, destinationDirectory, entryPath.getPathFragment());
      }
    } catch (IOException e) {
      throw new RepositoryFunctionException(new IOException(
          String.format("Error extracting %s to %s: %s",
              descriptor.archivePath(), destinationDirectory, e.getMessage())),
          Transience.TRANSIENT);
    }

    if (prefix.isPresent() && !foundPrefix) {
      throw new RepositoryFunctionException(
          new IOException("Prefix " + prefix.get() + " was given, but not found in the zip"),
          Transience.PERSISTENT);
    }

    return destinationDirectory;
  }
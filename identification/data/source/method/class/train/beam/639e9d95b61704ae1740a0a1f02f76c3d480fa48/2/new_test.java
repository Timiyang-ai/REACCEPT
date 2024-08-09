  public List<ResourceId> generateDestinationFilenames(FilenamePolicy policy, int numFiles) {
    List<ResourceId> filenames = new ArrayList<>();
    for (int i = 0; i < numFiles; i++) {
      filenames.add(policy.unwindowedFilename(i, numFiles, CompressionType.UNCOMPRESSED));
    }
    return filenames;
  }
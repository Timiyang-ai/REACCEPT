@Override
  public URI uploadPackage() throws UploaderException {
    // first, check if the topology package exists
    boolean fileExists = new File(topologyPackageLocation).isFile();
    if (!fileExists) {
      throw new UploaderException(
          String.format("Topology package does not exist at '%s'", topologyPackageLocation));
    }

    // get the directory containing the file
    Path filePath = Paths.get(destTopologyFile);
    File parentDirectory = filePath.getParent().toFile();
    assert parentDirectory != null;

    // if the dest directory does not exist, create it.
    if (!parentDirectory.exists()) {
      LOG.fine(String.format(
          "Working directory does not exist. Creating it now at %s", parentDirectory.getPath()));
      if (!parentDirectory.mkdirs()) {
        throw new UploaderException(
            String.format("Failed to create directory for topology package at %s",
                parentDirectory.getPath()));
      }
    }

    // if the dest file exists, write a log message
    fileExists = new File(filePath.toString()).isFile();
    if (fileExists) {
      LOG.fine(String.format("Target topology package already exists at '%s'. Overwriting it now",
          filePath.toString()));
    }

    // copy the topology package to target working directory
    LOG.fine(String.format("Copying topology package at '%s' to target working directory '%s'",
        topologyPackageLocation, filePath.toString()));

    Path source = Paths.get(topologyPackageLocation);
    try {
      CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
      Files.copy(source, filePath, options);
    } catch (IOException e) {
      throw new UploaderException(
            String.format("Unable to copy topology file from '%s' to '%s'",
                source, filePath), e);
    }

    return getUri(destTopologyFile);
  }
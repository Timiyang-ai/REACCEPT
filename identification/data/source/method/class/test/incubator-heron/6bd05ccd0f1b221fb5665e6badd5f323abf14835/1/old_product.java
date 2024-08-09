@Override
  public URI uploadPackage() {
    // first, check if the topology package exists
    boolean fileExists = new File(topologyPackageLocation).isFile();
    if (!fileExists) {
      LOG.info("Topology file " + topologyPackageLocation + " does not exist.");
      return null;
    }

    // get the directory containing the file
    Path filePath = Paths.get(destTopologyFile);
    File parentDirectory = filePath.getParent().toFile();
    assert parentDirectory != null;

    // if the dest directory does not exist, create it.
    if (!parentDirectory.exists()) {
      LOG.fine("The working directory does not exist; creating it.");
      if (!parentDirectory.mkdirs()) {
        LOG.severe("Failed to create directory: " + parentDirectory.getPath());
        return null;
      }
    }

    // if the dest file exists, write a log message
    fileExists = new File(filePath.toString()).isFile();
    if (fileExists) {
      LOG.fine("Target topology file " + filePath.toString() + " exists, overwriting...");
    }

    // copy the topology package to target working directory
    LOG.fine("Copying topology " + topologyPackageLocation
        + " package to target working directory " + filePath.toString());

    Path source = Paths.get(topologyPackageLocation);
    try {
      CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
      Files.copy(source, filePath, options);
    } catch (IOException ex) {
      LOG.info("Unable to copy: " + source.toString() + " " + ex);
      return null;
    }

    return getUri(destTopologyFile);
  }
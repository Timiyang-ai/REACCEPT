public void format() throws IOException {
    URI location = getLocation();
    LOG.info("Formatting {}", location);
    if (mUfs.isDirectory(location.toString())) {
      for (UfsStatus status : mUfs.listStatus(location.toString())) {
        String childPath = URIUtils.appendPathOrDie(location, status.getName()).toString();
        if (status.isDirectory()
            && !mUfs.deleteDirectory(childPath, DeleteOptions.defaults().setRecursive(true))
            || status.isFile() && !mUfs.deleteFile(childPath)) {
          throw new IOException(String.format("Failed to delete %s", childPath));
        }
      }
    } else if (!mUfs.mkdirs(location.toString())) {
      throw new IOException(String.format("Failed to create %s", location));
    }

    // Create a breadcrumb that indicates that the journal folder has been formatted.
    UnderFileSystemUtils.touch(mUfs, URIUtils.appendPathOrDie(location,
        Configuration.get(PropertyKey.MASTER_FORMAT_FILE_PREFIX) + System.currentTimeMillis())
        .toString());
  }
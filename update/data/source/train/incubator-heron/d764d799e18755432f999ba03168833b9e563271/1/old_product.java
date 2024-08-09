public static boolean setupWorkingDirectory(
      String workingDirectory,
      String coreReleasePackageURL,
      String coreReleaseDestination,
      String topologyPackageURL,
      String topologyPackageDestination,
      boolean isVerbose) {
    // if the working directory does not exist, create it.
    if (!FileUtils.isDirectoryExists(workingDirectory)) {
      LOG.fine("The working directory does not exist; creating it.");
      if (!FileUtils.createDirectory(workingDirectory)) {
        LOG.severe("Failed to create directory: " + workingDirectory);
        return false;
      }
    }

    // Curl and extract heron core release package and topology package
    // And then delete the downloaded release package
    boolean ret =
        curlAndExtractPackage(
            workingDirectory, coreReleasePackageURL, coreReleaseDestination, true, isVerbose)
            &&
            curlAndExtractPackage(
                workingDirectory, topologyPackageURL, topologyPackageDestination, true, isVerbose);

    return ret;
  }
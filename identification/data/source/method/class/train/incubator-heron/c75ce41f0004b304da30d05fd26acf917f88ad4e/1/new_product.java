protected boolean setupWorkingDirectory() {
    // get the path of core release URI
    String coreReleasePackageURI = SlurmContext.corePackageUri(config);

    // form the target dest core release file name
    String coreReleaseFileDestination = Paths.get(
        topologyWorkingDirectory, "heron-core.tar.gz").toString();

    // Form the topology package's URI
    String topologyPackageURI = Runtime.topologyPackageUri(runtime).toString();

    // form the target topology package file name
    String topologyPackageDestination = Paths.get(
        topologyWorkingDirectory, "topology.tar.gz").toString();

    if (!SchedulerUtils.createOrCleanDirectory(topologyWorkingDirectory)) {
      return false;
    }

    final boolean isVerbose = Context.verbose(config);
    if (!SchedulerUtils.extractPackage(topologyWorkingDirectory, coreReleasePackageURI,
        coreReleaseFileDestination, true, isVerbose)) {
      return false;
    }

    return SchedulerUtils.extractPackage(topologyWorkingDirectory, topologyPackageURI,
        topologyPackageDestination, true, isVerbose);
  }
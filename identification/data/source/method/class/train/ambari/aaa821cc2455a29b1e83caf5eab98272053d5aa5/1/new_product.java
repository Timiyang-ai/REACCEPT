@Override
  public boolean isApplicable(PrereqCheckRequest request) throws AmbariException {
    if (!super.isApplicable(request, Arrays.asList("YARN"), true)) {
      return false;
    }

    final Cluster cluster = clustersProvider.get().getCluster(request.getClusterName());

    String minApplicableStackVersion = null;
    PrerequisiteCheckConfig prerequisiteCheckConfig = request.getPrerequisiteCheckConfig();
    Map<String, String> checkProperties = null;
    if(prerequisiteCheckConfig != null) {
      checkProperties = prerequisiteCheckConfig.getCheckProperties(this.getClass().getName());
    }
    if(checkProperties != null && checkProperties.containsKey(MIN_APPLICABLE_STACK_VERSION_PROPERTY_NAME)) {
      minApplicableStackVersion = checkProperties.get(MIN_APPLICABLE_STACK_VERSION_PROPERTY_NAME);
    }

    // Due to the introduction of YARN Timeline state recovery only from certain
    // stack-versions onwards, this check is not applicable to earlier versions
    // of the stack.
    // Applicable only if min-applicable-stack-version config property is not defined, or
    // version equals or exceeds the configured version.
    if(minApplicableStackVersion != null && !minApplicableStackVersion.isEmpty()) {
      String[] minStack = minApplicableStackVersion.split("-");
      if(minStack.length == 2) {
        String minStackName = minStack[0];
        String minStackVersion = minStack[1];
        Service yarnService = cluster.getService("YARN");
        String stackName = yarnService.getDesiredStackId().getStackName();
        if (minStackName.equals(stackName)) {
          String currentRepositoryVersion = yarnService.getDesiredRepositoryVersion().getVersion();
          return VersionUtils.compareVersions(currentRepositoryVersion, minStackVersion) >= 0;
        }
      }
    }

    return true;
  }
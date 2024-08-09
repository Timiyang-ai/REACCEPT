@Override
  public RequestStatus updateResources(Request request, Predicate predicate)
      throws SystemException, UnsupportedPropertyException,
      NoSuchResourceException, NoSuchParentResourceException {
    try {
      Iterator<Map<String, Object>> iterator = request.getProperties().iterator();
      String clName;
      final String desiredRepoVersion;
      if (request.getProperties().size() != 1) {
        throw new UnsupportedOperationException("Multiple requests cannot be executed at the same time.");
      }
      Map<String, Object> propertyMap = iterator.next();

      Set<String> requiredProperties = new HashSet<String>() {{
        add(CLUSTER_STACK_VERSION_CLUSTER_NAME_PROPERTY_ID);
        add(CLUSTER_STACK_VERSION_REPOSITORY_VERSION_PROPERTY_ID);
        add(CLUSTER_STACK_VERSION_STATE_PROPERTY_ID);
      }};

      for (String requiredProperty : requiredProperties) {
        if (!propertyMap.containsKey(requiredProperty)) {
          throw new IllegalArgumentException(
                  String.format("The required property %s is not defined",
                          requiredProperty));
        }
      }

      clName = (String) propertyMap.get(CLUSTER_STACK_VERSION_CLUSTER_NAME_PROPERTY_ID);
      String desiredDisplayRepoVersion = (String) propertyMap.get(CLUSTER_STACK_VERSION_REPOSITORY_VERSION_PROPERTY_ID);
      RepositoryVersionEntity rve = repositoryVersionDAO.findByDisplayName(desiredDisplayRepoVersion);
      if (rve == null) {
        throw new IllegalArgumentException(
                  String.format("Repository version with display name %s does not exist",
                          desiredDisplayRepoVersion));
      }
      desiredRepoVersion = rve.getVersion();
      String newStateStr = (String) propertyMap.get(CLUSTER_STACK_VERSION_STATE_PROPERTY_ID);

      LOG.info("Initiating finalization for manual upgrade to version {} for cluster {}",
              desiredRepoVersion, clName);

      // First, set desired cluster stack version to enable cross-stack upgrade
      StackId stackId = rve.getStackId();
      Cluster cluster = getManagementController().getClusters().getCluster(clName);
      cluster.setDesiredStackVersion(stackId);

      Map<String, String> args = new HashMap<String, String>();
      if (newStateStr.equals(RepositoryVersionState.CURRENT.toString())) {
        // Finalize upgrade workflow
        args.put(FinalizeUpgradeAction.UPGRADE_DIRECTION_KEY, "upgrade");
      } else if (newStateStr.equals(RepositoryVersionState.INSTALLED.toString())) {
        // Finalize downgrade workflow
        args.put(FinalizeUpgradeAction.UPGRADE_DIRECTION_KEY, "downgrade");
      } else {
        throw new IllegalArgumentException(
          String.format("Invalid desired state %s. Should be either CURRENT or INSTALLED",
                  newStateStr));
      }

      // Get a host name to populate the hostrolecommand table's hostEntity.
      String defaultHostName = null;
      // TODO: remove direct access to cluster entity completely
      ClusterEntity clusterEntity = clusterDAO.findByName(clName);
      List<HostEntity> hosts = new ArrayList(clusterEntity.getHostEntities());
      if (hosts != null && !hosts.isEmpty()) {
        Collections.sort(hosts);
        defaultHostName = hosts.get(0).getHostName();
      }
      if (defaultHostName == null) {
        throw new AmbariException("Could not find at least one host to set the command for");
      }

      args.put(FinalizeUpgradeAction.VERSION_KEY, desiredRepoVersion);
      args.put(FinalizeUpgradeAction.CLUSTER_NAME_KEY, clName);

      ExecutionCommand command = new ExecutionCommand();
      command.setCommandParams(args);
      command.setClusterName(clName);
      finalizeUpgradeAction.setExecutionCommand(command);
      
      HostRoleCommand hostRoleCommand = hostRoleCommandFactory.create(defaultHostName,
              Role.AMBARI_SERVER_ACTION, null, null);
      finalizeUpgradeAction.setHostRoleCommand(hostRoleCommand);

      CommandReport report = finalizeUpgradeAction.execute(null);

      LOG.info("Finalize output:");
      LOG.info("STDOUT: {}", report.getStdOut());
      LOG.info("STDERR: {}", report.getStdErr());

      if (report.getStatus().equals(HostRoleStatus.COMPLETED.toString())) {
        return getRequestStatus(null);
      } else {
        String detailedOutput = "Finalization failed. More details: \n" +
                "STDOUT: " + report.getStdOut() + "\n" +
                "STDERR: " + report.getStdErr();
        throw new SystemException(detailedOutput);
      }
    } catch (AmbariException e) {
      throw new SystemException("Can not perform request", e);
    } catch (InterruptedException e) {
      throw new SystemException("Can not perform request", e);
    }
  }
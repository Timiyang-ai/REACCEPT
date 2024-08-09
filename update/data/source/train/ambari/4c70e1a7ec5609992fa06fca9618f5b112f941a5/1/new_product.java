@Override
  public CommandReport execute(ConcurrentMap<String, Object> requestSharedDataContext)
      throws AmbariException, InterruptedException {

    String clusterName = getExecutionCommand().getClusterName();
    Cluster cluster = m_clusters.getCluster(clusterName);

    UpgradeContext upgradeContext = getUpgradeContext(cluster);
    UpgradePack upgradePack = upgradeContext.getUpgradePack();
    StackId stackId = upgradePack.getOwnerStackId();
    StackInfo stackInfo = m_metainfoProvider.get().getStack(stackId);

    ClassLoader pluginClassLoader = stackInfo.getLibraryClassLoader();
    if (null == pluginClassLoader) {
      return createCommandReport(0, HostRoleStatus.FAILED, "{}", "",
          "There is no plugin classloader defined for stack " + stackId);
    }

    final UpgradeAction upgradeAction;
    final String pluginClassName = getActionClassName();

    try {
      @SuppressWarnings("unchecked")
      Class<? extends UpgradeAction> upgradeActionClass = (Class<? extends UpgradeAction>) pluginClassLoader.loadClass(
          pluginClassName);

      upgradeAction = upgradeActionClass.newInstance();
    } catch (Exception exception) {
      LOG.error("Unable to load the upgrade action {}", pluginClassName, exception);

      return createCommandReport(0, HostRoleStatus.FAILED, "{}", "",
          "Unable to load the upgrade class  " + pluginClassName);
    }

    String standardOutput;

    try {
      ClusterInformation clusterInformation = cluster.buildClusterInformation();
      UpgradeActionOperations upgradeActionOperations = upgradeAction.getOperations(
          clusterInformation, upgradeContext.buildUpgradeInformation());

      // update configurations
      changeConfigurations(cluster, upgradeActionOperations.getConfigurationChanges(), upgradeContext);
      removeConfigurationTypes(cluster, upgradeActionOperations.getConfigurationTypeRemovals());

      standardOutput = "Successfully executed " + pluginClassName;
      if(null != upgradeActionOperations.getStandardOutput()) {
        standardOutput = upgradeActionOperations.getStandardOutput();
      }
    } catch (UpgradeActionException exception) {
      LOG.error("Unable to run the upgrade action {}", pluginClassName, exception);
      return createCommandReport(0, HostRoleStatus.FAILED, "{}", "", exception.getMessage());
    } catch (Exception exception) {
      LOG.error("Unable to run the upgrade action {}", pluginClassName, exception);
      String standardError = "Unable to run " + pluginClassName;
      return createCommandReport(0, HostRoleStatus.FAILED, "{}", "", standardError);
    }

    // !!! it's stupid that we have to do this
    agentConfigsHolder.updateData(cluster.getClusterId(),
        cluster.getHosts().stream().map(Host::getHostId).collect(Collectors.toList()));

    return createCommandReport(0, HostRoleStatus.COMPLETED, "{}", standardOutput, "");
  }
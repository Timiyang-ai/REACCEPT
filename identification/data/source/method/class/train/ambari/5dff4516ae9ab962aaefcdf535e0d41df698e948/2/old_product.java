public boolean isOperationAllowed(Cluster cluster,
      RequestOperationLevel levelObj, RequestResourceFilter reqFilter,
      String serviceName, String componentName, String hostname)
      throws AmbariException {
    Resource.Type level;
    if (levelObj == null) {
      level = guessOperationLevel(reqFilter);
    } else {
      level = levelObj.getLevel();
    }
    return isOperationAllowed(cluster, level, serviceName, componentName,
        hostname);
  }
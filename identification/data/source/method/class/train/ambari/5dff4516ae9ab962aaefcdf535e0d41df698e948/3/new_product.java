boolean isOperationAllowed(Cluster cluster, Resource.Type level,
      String serviceGroupName, String serviceName, String componentName, String hostname)
      throws AmbariException {
//    serviceGroupName != null && !serviceGroupName.isEmpty() &&
//    TODO add service group null checks when the UI is updated
    if (serviceName != null && !serviceName.isEmpty()) {
      Service service = cluster.getService(serviceGroupName, serviceName);
      if (componentName != null && !componentName.isEmpty()) {
        ServiceComponentHost sch = service.getServiceComponent(componentName).getServiceComponentHost(
            hostname);
        return isOperationAllowed(level, sch);
      } else { // Only service name is defined
        return isOperationAllowed(level, service);
      }
    } else { // Service is not defined, using host
      Host host = clusters.getHost(hostname);
      return isOperationAllowed(host, cluster.getClusterId(), level);
    }
  }
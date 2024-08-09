public Set<String> invalidateHosts(AlertDefinitionEntity definition) {
    long clusterId = definition.getClusterId();
    Set<String> invalidatedHosts = new HashSet<String>();

    Cluster cluster = null;
    Map<String, Host> hosts = null;
    String clusterName = null;
    try {
      cluster = m_clusters.getClusterById(clusterId);
      if (null != cluster) {
        clusterName = cluster.getClusterName();
        hosts = m_clusters.getHostsForCluster(clusterName);
      }

      if (null == cluster) {
        LOG.warn("Unable to lookup cluster with ID {}", clusterId);
      }
    } catch (Exception exception) {
      LOG.error("Unable to lookup cluster with ID {}", clusterId, exception);
    }

    if (null == cluster) {
      return invalidatedHosts;
    }

    // intercept host agent alerts; they affect all hosts
    String definitionServiceName = definition.getServiceName();
    String definitionComponentName = definition.getComponentName();
    if (Services.AMBARI.equals(definitionServiceName)
        && Components.AMBARI_AGENT.equals(definitionComponentName)) {

      invalidateAll();
      invalidatedHosts.addAll(hosts.keySet());
      return invalidatedHosts;
    }

    // find all hosts that have the matching service and component
    for (String hostName : hosts.keySet()) {
      List<ServiceComponentHost> hostComponents = cluster.getServiceComponentHosts(hostName);
      if (null == hostComponents || hostComponents.size() == 0) {
        continue;
      }

      // if a host has a matching service/component, invalidate it
      for (ServiceComponentHost component : hostComponents) {
        String serviceName = component.getServiceName();
        String componentName = component.getServiceComponentName();
        if (serviceName.equals(definitionServiceName)
            && componentName.equals(definitionComponentName)) {
          invalidate(clusterName, hostName);
          invalidatedHosts.add(hostName);
        }
      }
    }

    // get the service that this alert definition is associated with
    Map<String, Service> services = cluster.getServices();
    Service service = services.get(definitionServiceName);
    if (null == service) {
      LOG.warn("The alert definition {} has an unknown service of {}",
          definition.getDefinitionName(), definitionServiceName);

      return invalidatedHosts;
    }

    // get all master components of the definition's service; any hosts that
    // run the master should be invalidated as well
    Map<String, ServiceComponent> components = service.getServiceComponents();
    if (null != components) {
      for (Entry<String, ServiceComponent> component : components.entrySet()) {
        if (component.getValue().isMasterComponent()) {
          Map<String, ServiceComponentHost> componentHosts = component.getValue().getServiceComponentHosts();
          if (null != componentHosts) {
            for (String componentHost : componentHosts.keySet()) {
              invalidate(clusterName, componentHost);
              invalidatedHosts.add(componentHost);
            }
          }
        }
      }
    }

    return invalidatedHosts;
  }
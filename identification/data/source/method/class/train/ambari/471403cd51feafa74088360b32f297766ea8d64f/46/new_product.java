public synchronized void createServices(Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return;
    }
    Clusters clusters = getManagementController().getClusters();
    // do all validation checks
    validateCreateRequests(requests, clusters);

    for (ServiceRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());

      // Already checked that service does not exist
      Service s = cluster.addService(request.getServiceName());

      // Initialize service widgets
      getManagementController().initializeWidgetsAndLayouts(cluster, s);
    }
  }
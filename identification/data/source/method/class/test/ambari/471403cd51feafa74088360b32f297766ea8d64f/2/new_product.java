public synchronized void createServices(Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return;
    }
    Clusters clusters = getManagementController().getClusters();
    // do all validation checks
    validateCreateRequests(requests, clusters);

    ServiceFactory serviceFactory = getManagementController().getServiceFactory();
    for (ServiceRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());

      State state = State.INIT;

      // Already checked that service does not exist
      Service s = serviceFactory.createNew(cluster, request.getServiceName());

      s.setDesiredState(state);
      s.setDesiredStackVersion(cluster.getDesiredStackVersion());
      s.persist();
      // Initialize service widgets
      getManagementController().initializeWidgetsAndLayouts(cluster, s);
    }
  }
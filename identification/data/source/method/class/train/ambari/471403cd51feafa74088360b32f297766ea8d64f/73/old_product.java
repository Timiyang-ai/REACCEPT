public synchronized void createServices(Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return;
    }

    Clusters       clusters       = getManagementController().getClusters();
    AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();

    // do all validation checks
    Map<String, Set<String>> serviceNames = new HashMap<String, Set<String>>();
    Set<String> duplicates = new HashSet<String>();
    for (ServiceRequest request : requests) {
      if (request.getClusterName() == null
          || request.getClusterName().isEmpty()
          || request.getServiceName() == null
          || request.getServiceName().isEmpty()) {
        throw new IllegalArgumentException("Cluster name and service name"
            + " should be provided when creating a service");
      }

      if (LOG.isDebugEnabled()) {
        LOG.debug("Received a createService request"
            + ", clusterName=" + request.getClusterName()
            + ", serviceName=" + request.getServiceName()
            + ", request=" + request);
      }

      if(!AuthorizationHelper.isAuthorized(ResourceType.CLUSTER, getClusterResourceId(request.getClusterName()), RoleAuthorization.SERVICE_ADD_DELETE_SERVICES)) {
        throw new AuthorizationException("The user is not authorized to create services");
      }

      if (!serviceNames.containsKey(request.getClusterName())) {
        serviceNames.put(request.getClusterName(), new HashSet<String>());
      }
      if (serviceNames.get(request.getClusterName())
          .contains(request.getServiceName())) {
        // throw error later for dup
        duplicates.add(request.getServiceName());
        continue;
      }
      serviceNames.get(request.getClusterName()).add(request.getServiceName());

      if (request.getDesiredState() != null
          && !request.getDesiredState().isEmpty()) {
        State state = State.valueOf(request.getDesiredState());
        if (!state.isValidDesiredState()
            || state != State.INIT) {
          throw new IllegalArgumentException("Invalid desired state"
              + " only INIT state allowed during creation"
              + ", providedDesiredState=" + request.getDesiredState());
        }
      }

      Cluster cluster;
      try {
        cluster = clusters.getCluster(request.getClusterName());
      } catch (ClusterNotFoundException e) {
        throw new ParentObjectNotFoundException("Attempted to add a service to a cluster which doesn't exist", e);
      }
      try {
        Service s = cluster.getService(request.getServiceName());
        if (s != null) {
          // throw error later for dup
          duplicates.add(request.getServiceName());
          continue;
        }
      } catch (ServiceNotFoundException e) {
        // Expected
      }

      StackId stackId = cluster.getDesiredStackVersion();
      if (!ambariMetaInfo.isValidService(stackId.getStackName(),
          stackId.getStackVersion(), request.getServiceName())) {
        throw new IllegalArgumentException("Unsupported or invalid service"
            + " in stack"
            + ", clusterName=" + request.getClusterName()
            + ", serviceName=" + request.getServiceName()
            + ", stackInfo=" + stackId.getStackId());
      }
    }

    // ensure only a single cluster update
    if (serviceNames.size() != 1) {
      throw new IllegalArgumentException("Invalid arguments, updates allowed"
          + "on only one cluster at a time");
    }

    // Validate dups
    if (!duplicates.isEmpty()) {
      StringBuilder svcNames = new StringBuilder();
      boolean first = true;
      for (String svcName : duplicates) {
        if (!first) {
          svcNames.append(",");
        }
        first = false;
        svcNames.append(svcName);
      }
      String clusterName = requests.iterator().next().getClusterName();
      String msg;
      if (duplicates.size() == 1) {
        msg = "Attempted to create a service which already exists: "
            + ", clusterName=" + clusterName  + " serviceName=" + svcNames.toString();
      } else {
        msg = "Attempted to create services which already exist: "
            + ", clusterName=" + clusterName  + " serviceNames=" + svcNames.toString();
      }
      throw new DuplicateResourceException(msg);
    }

    ServiceFactory serviceFactory = getManagementController().getServiceFactory();

    // now to the real work
    for (ServiceRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());

      State state = State.INIT;

      // Already checked that service does not exist
      Service s = serviceFactory.createNew(cluster, request.getServiceName());

      s.setDesiredState(state);
      s.setDesiredStackVersion(cluster.getDesiredStackVersion());
      cluster.addService(s);
      // Initialize service widgets
      getManagementController().initializeWidgetsAndLayouts(cluster, s);

      s.persist();
    }
  }
private Set<ServiceComponentResponse> getComponents(
      ServiceComponentRequest request) throws AmbariException {
    if (request.getClusterName() == null
        || request.getClusterName().isEmpty()) {
      throw new IllegalArgumentException("Invalid arguments, cluster name"
          + " should be non-null");
    }

    Clusters clusters = getManagementController().getClusters();
    AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();

    final Cluster cluster;
    try {
      cluster = clusters.getCluster(request.getClusterName());
    } catch (ObjectNotFoundException e) {
      throw new ParentObjectNotFoundException("Parent Cluster resource doesn't exist", e);
    }

    Set<ServiceComponentResponse> response =
        new HashSet<ServiceComponentResponse>();
    String category = null;

    StackId stackId = cluster.getDesiredStackVersion();

    if (request.getComponentName() != null) {
      if (request.getServiceName() == null) {
        String serviceName =
            ambariMetaInfo.getComponentToService(stackId.getStackName(),
                stackId.getStackVersion(), request.getComponentName());
        if (LOG.isDebugEnabled()) {
          LOG.debug("Looking up service name for component"
              + ", componentName=" + request.getComponentName()
              + ", serviceName=" + serviceName);
        }
        if (serviceName == null
            || serviceName.isEmpty()) {
          throw new ObjectNotFoundException("Could not find service for component"
              + ", componentName=" + request.getComponentName()
              + ", clusterName=" + cluster.getClusterName()
              + ", stackInfo=" + stackId.getStackId());
        }
        request.setServiceName(serviceName);
      }

      final Service s;
      try {
        s = cluster.getService(request.getServiceName());
      } catch (ObjectNotFoundException e) {
        throw new ParentObjectNotFoundException("Parent Service resource doesn't exist", e);
      }

      ServiceComponent sc = s.getServiceComponent(request.getComponentName());
      ServiceComponentResponse serviceComponentResponse = sc.convertToResponse();

      try {
        ComponentInfo componentInfo = ambariMetaInfo.getComponent(stackId.getStackName(),
            stackId.getStackVersion(), s.getName(), request.getComponentName());
        category = componentInfo.getCategory();
        if (category != null) {
          serviceComponentResponse.setCategory(category);
        }
      } catch (ObjectNotFoundException e) {
        // nothing to do, component doesn't exist
      }

      response.add(serviceComponentResponse);
      return response;
    }

    boolean checkDesiredState = false;
    State desiredStateToCheck = null;
    if (request.getDesiredState() != null
        && !request.getDesiredState().isEmpty()) {
      desiredStateToCheck = State.valueOf(request.getDesiredState());
      if (!desiredStateToCheck.isValidDesiredState()) {
        throw new IllegalArgumentException("Invalid arguments, invalid desired"
            + " state, desiredState=" + desiredStateToCheck);
      }
      checkDesiredState = true;
    }

    Set<Service> services = new HashSet<Service>();
    if (request.getServiceName() != null
        && !request.getServiceName().isEmpty()) {
      try {
        services.add(cluster.getService(request.getServiceName()));
      } catch (ObjectNotFoundException e) {
        throw new ParentObjectNotFoundException("Could not find service", e);
      }
    } else {
      services.addAll(cluster.getServices().values());
    }

    for (Service s : services) {
      // filter on request.getDesiredState()
      for (ServiceComponent sc : s.getServiceComponents().values()) {
        if (checkDesiredState
            && (desiredStateToCheck != sc.getDesiredState())) {
          // skip non matching state
          continue;
        }

        ServiceComponentResponse serviceComponentResponse = sc.convertToResponse();
        try {
          ComponentInfo componentInfo = ambariMetaInfo.getComponent(stackId.getStackName(),
              stackId.getStackVersion(), s.getName(), sc.getName());
          category = componentInfo.getCategory();
          if (category != null) {
            serviceComponentResponse.setCategory(category);
          }
        } catch (ObjectNotFoundException e) {
          // component doesn't exist, nothing to do
        }
        String requestedCategory = request.getComponentCategory();
        if (requestedCategory != null && !requestedCategory.isEmpty() &&
            category != null && !requestedCategory.equalsIgnoreCase(category)) {
          continue;
        }

        response.add(serviceComponentResponse);
      }
    }
    return response;
  }
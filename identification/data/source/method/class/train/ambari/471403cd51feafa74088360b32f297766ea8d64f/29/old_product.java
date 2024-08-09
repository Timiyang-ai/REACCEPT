private Set<ServiceResponse> getServices(ServiceRequest request)
      throws AmbariException {
    if (request.getClusterName() == null) {
      throw new AmbariException("Invalid arguments, cluster name"
          + " cannot be null");
    }
    Clusters clusters    = getManagementController().getClusters();
    String clusterId = request.getClusterName();
    String serviceGroupName = request.getServiceGroupName();

    final Cluster cluster;
    try {
      cluster = clusters.getCluster(clusterId);
    } catch (ObjectNotFoundException e) {
      throw new ParentObjectNotFoundException("Parent Cluster resource doesn't exist", e);
    }

    Set<ServiceResponse> response = new HashSet<>();
    if (request.getServiceName() != null) {
      Service s = cluster.getService(request.getServiceGroupName(), request.getServiceName());
      response.add(s.convertToResponse());
      return response;
    }

    // TODO support search on predicates?

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

    Collection<Service> clusterServices;
    if(request.getServiceGroupName() != null){
     clusterServices = cluster.getServicesByServiceGroup(serviceGroupName);
    }else{
      clusterServices = cluster.getServices().values();
    }
    for (Service s : clusterServices) {
      if (checkDesiredState
          && (desiredStateToCheck != s.getDesiredState())) {
        // skip non matching state
        continue;
      }
      ServiceResponse serviceResponse = s.convertToResponse();
      // TODO: Open it when we dont want to support queries for servies and components at cluster level.
      // UI as of now makes that calls for optimizations purposes.
      /*
      // Check if the received response for service's servicegroup is same as passed-in servicegroup name.
      String retrivedSvcGrp = serviceResponse.getServiceGroupName();
      if (retrivedSvcGrp == null) {
        throw new NullPointerException("'ServiceGroupName' : null in cluster : " + cluster.getClusterName() +
                " for retrieved Service : "+s.getServiceName());
      }
      if (!retrivedSvcGrp.equals(request.getServiceGroupName())) {
        throw new ServiceNotFoundException(cluster.getClusterName(), s.getServiceName());
      }
      */
      response.add(serviceResponse);
    }
    return response;
  }
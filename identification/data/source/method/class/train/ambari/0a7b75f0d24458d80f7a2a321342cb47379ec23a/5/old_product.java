protected RequestStatusResponse deleteServices(Set<ServiceRequest> request)
      throws AmbariException {

    Clusters clusters    = getManagementController().getClusters();

    for (ServiceRequest serviceRequest : request) {
      if (StringUtils.isEmpty(serviceRequest.getClusterName()) || StringUtils.isEmpty(serviceRequest.getServiceName())) {
        // FIXME throw correct error
        throw new AmbariException("invalid arguments");
      } else {
        clusters.getCluster(serviceRequest.getClusterName()).deleteService(serviceRequest.getServiceName());
      }
    }
    return null;
  }
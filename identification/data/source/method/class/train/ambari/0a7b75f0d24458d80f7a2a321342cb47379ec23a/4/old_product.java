protected RequestStatusResponse deleteServices(Set<ServiceRequest> request)
      throws AmbariException {

    Clusters clusters    = getManagementController().getClusters();

    Set<Service> removable = new HashSet<Service>();

    for (ServiceRequest serviceRequest : request) {
      if (StringUtils.isEmpty(serviceRequest.getClusterName()) || StringUtils.isEmpty(serviceRequest.getServiceName())) {
        // FIXME throw correct error
        throw new AmbariException("invalid arguments");
      } else {

        Service service = clusters.getCluster(
            serviceRequest.getClusterName()).getService(
                serviceRequest.getServiceName());

        if (!service.getDesiredState().isRemovableState()) {
          throw new AmbariException("Cannot remove " + service.getName() + ". Desired state " +
              service.getDesiredState() + " is not removable.  Service must be stopped or disabled.");
        } else {
          for (ServiceComponent sc : service.getServiceComponents().values()) {
            if (!sc.canBeRemoved()) {
              throw new AmbariException ("Cannot remove " +
                  serviceRequest.getClusterName() + "/" + serviceRequest.getServiceName() +
                  ". " + sc.getName() + " is in a non-removable state.");
            }
          }
        }

        removable.add(service);
      }
    }

    for (Service service : removable) {
      service.getCluster().deleteService(service.getName());
    }

    return null;
  }
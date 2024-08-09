protected RequestStatusResponse deleteServices(Set<ServiceRequest> request)
      throws AmbariException, AuthorizationException {

    Clusters clusters    = getManagementController().getClusters();

    Set<Service> removable = new HashSet<Service>();

    for (ServiceRequest serviceRequest : request) {
      if (StringUtils.isEmpty(serviceRequest.getClusterName()) || StringUtils.isEmpty(serviceRequest.getServiceName())) {
        // FIXME throw correct error
        throw new AmbariException("invalid arguments");
      } else {

        if(!AuthorizationHelper.isAuthorized(ResourceType.CLUSTER, getClusterId(serviceRequest.getClusterName()), RoleAuthorization.SERVICE_ADD_DELETE_SERVICES)) {
          throw new AuthorizationException("The user is not authorized to delete services");
        }

        Service service = clusters.getCluster(
            serviceRequest.getClusterName()).getService(
                serviceRequest.getServiceName());

        //
        // Run through the list of service components. If all components are in removable state,
        // the service can be deleted, irrespective of it's state.
        //
        for (ServiceComponent sc : service.getServiceComponents().values()) {
          if (!sc.canBeRemoved()) {
            throw new AmbariException ("Cannot remove " +
                    serviceRequest.getClusterName() + "/" + serviceRequest.getServiceName() +
                    ". " + sc.getName() + " is in a non-removable state.");
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
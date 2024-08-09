protected RequestStatusResponse deleteServices(Set<ServiceRequest> request)
      throws AmbariException, AuthorizationException {

    Clusters clusters    = getManagementController().getClusters();

    Set<Service> removable = new HashSet<>();

    for (ServiceRequest serviceRequest : request) {
      if (StringUtils.isEmpty(serviceRequest.getClusterName()) || StringUtils.isEmpty(serviceRequest.getServiceName())) {
        // FIXME throw correct error
        throw new AmbariException("invalid arguments");
      } else {

        if(!AuthorizationHelper.isAuthorized(ResourceType.CLUSTER, getClusterResourceId(serviceRequest.getClusterName()), RoleAuthorization.SERVICE_ADD_DELETE_SERVICES)) {
          throw new AuthorizationException("The user is not authorized to delete services");
        }

        Service service = clusters.getCluster(
            serviceRequest.getClusterName()).getService(
                serviceRequest.getServiceName());

        //
        // Run through the list of service component hosts. If all host components are in removable state,
        // the service can be deleted, irrespective of it's state.
        //
        boolean isServiceRemovable = true;

        for (ServiceComponent sc : service.getServiceComponents().values()) {
          Map<String, ServiceComponentHost> schHostMap = sc.getServiceComponentHosts();

          for (Map.Entry<String, ServiceComponentHost> entry : schHostMap.entrySet()) {
            ServiceComponentHost sch = entry.getValue();
            if (!sch.canBeRemoved()) {
              String msg = "Cannot remove " + serviceRequest.getClusterName() + "/" + serviceRequest.getServiceName() +
                      ". " + sch.getServiceComponentName() + "on " + sch.getHost() + " is in " +
                      String.valueOf(sch.getDesiredState()) + " state.";
              LOG.error(msg);
              isServiceRemovable = false;
            }
          }
        }

        if (!isServiceRemovable) {
          throw new AmbariException ("Cannot remove " +
                  serviceRequest.getClusterName() + "/" + serviceRequest.getServiceName() +
                    ". " + "One or more host components are in a non-removable state.");
        }

        removable.add(service);
      }
    }

    DeleteHostComponentStatusMetaData deleteMetaData = new DeleteHostComponentStatusMetaData();
    for (Service service : removable) {
      service.getCluster().deleteService(service.getName(), deleteMetaData);
      STOMPComponentsDeleteHandler.processDeleteByMetaDataException(deleteMetaData);
    }
    STOMPComponentsDeleteHandler.processDeleteByMetaData(deleteMetaData);

    return null;
  }
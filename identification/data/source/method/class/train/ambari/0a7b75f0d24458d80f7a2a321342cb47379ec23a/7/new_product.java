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
        List<ServiceComponentHost> nonRemovableComponents =  service.getServiceComponents().values().stream()
          .flatMap(sch -> sch.getServiceComponentHosts().values().stream())
          .filter(sch -> !sch.canBeRemoved())
          .collect(Collectors.toList());

        if (!nonRemovableComponents.isEmpty()) {
          for (ServiceComponentHost sch: nonRemovableComponents){
            String msg = String.format("Cannot remove %s/%s. %s on %s is in %s state.",
              serviceRequest.getClusterName(), serviceRequest.getServiceName(), sch.getServiceComponentName(),
              sch.getHost(), String.valueOf(sch.getState()));

            LOG.error(msg);
          }

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
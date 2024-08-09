protected RequestStageContainer updateServices(RequestStageContainer requestStages, Set<ServiceRequest> requests,
                                                      Map<String, String> requestProperties, boolean runSmokeTest,
                                                      boolean reconfigureClients, boolean startDependencies) throws AmbariException, AuthorizationException {

    AmbariManagementController controller = getManagementController();

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return null;
    }

    Map<State, List<Service>> changedServices
        = new EnumMap<>(State.class);
    Map<State, List<ServiceComponent>> changedComps =
      new EnumMap<>(State.class);
    Map<String, Map<State, List<ServiceComponentHost>>> changedScHosts =
      new HashMap<>();
    Collection<ServiceComponentHost> ignoredScHosts =
      new ArrayList<>();

    Set<String> clusterIds = new HashSet<>();
    Map<String, Set<String>> serviceNames = new HashMap<>();
    Set<State> seenNewStates = new HashSet<>();
    Map<Service, Boolean> serviceCredentialStoreEnabledMap = new HashMap<>();

    // Determine operation level
    Resource.Type reqOpLvl;
    if (requestProperties.containsKey(RequestOperationLevel.OPERATION_LEVEL_ID)) {
      RequestOperationLevel operationLevel = new RequestOperationLevel(requestProperties);
      reqOpLvl = operationLevel.getLevel();
    } else {
      String message = "Can not determine request operation level. " +
              "Operation level property should " +
              "be specified for this request.";
      LOG.warn(message);
      reqOpLvl = Resource.Type.Cluster;
    }

    Clusters clusters = controller.getClusters();

    // We don't expect batch requests for different clusters, that's why
    // nothing bad should happen if value is overwritten few times
    for (ServiceRequest request : requests) {
      if (request.getClusterName() == null
          || request.getClusterName().isEmpty()
          || request.getServiceName() == null
          || request.getServiceName().isEmpty()) {
        throw new IllegalArgumentException("Invalid arguments, cluster name"
            + " and/or service name should be provided to update services");
      }

      LOG.info("Received a updateService request"
          + ", clusterName=" + request.getClusterName()
          + ", serviceGroupName=" + request.getServiceGroupName()
          + ", serviceName=" + request.getServiceName()
          + ", request=" + request);

      clusterIds.add(request.getClusterName());

      if (clusterIds.size() > 1) {
        throw new IllegalArgumentException("Updates to multiple clusters is not"
            + " supported");
      }

      if (!serviceNames.containsKey(request.getClusterName())) {
        serviceNames.put(request.getClusterName(), new HashSet<>());
      }

      if (serviceNames.get(request.getClusterName())
          .contains(request.getServiceName())) {
        // TODO throw single exception
        throw new IllegalArgumentException("Invalid request contains duplicate"
            + " service names");
      }
      serviceNames.get(request.getClusterName()).add(request.getServiceName());

      Cluster cluster = clusters.getCluster(request.getClusterName());
      Service s = cluster.getService(request.getServiceName());
      State oldState = s.getDesiredState();
      State newState = null;
      if (request.getDesiredState() != null) {
        newState = State.valueOf(request.getDesiredState());
        if (!newState.isValidDesiredState()) {
          throw new IllegalArgumentException("Invalid arguments, invalid"
              + " desired state, desiredState=" + newState);
        }
      }

      // Setting Maintenance state for service
      if (null != request.getMaintenanceState()) {
        if (!AuthorizationHelper.isAuthorized(ResourceType.CLUSTER, cluster.getResourceId(), RoleAuthorization.SERVICE_TOGGLE_MAINTENANCE)) {
          throw new AuthorizationException("The authenticated user is not authorized to toggle the maintainence state of services");
        }

        MaintenanceState newMaint = MaintenanceState.valueOf(request.getMaintenanceState());
        if (newMaint  != s.getMaintenanceState()) {
          if (newMaint.equals(MaintenanceState.IMPLIED_FROM_HOST)
              || newMaint.equals(MaintenanceState.IMPLIED_FROM_SERVICE)) {
            throw new IllegalArgumentException("Invalid arguments, can only set " +
              "maintenance state to one of " + EnumSet.of(MaintenanceState.OFF, MaintenanceState.ON));
          } else {
            s.setMaintenanceState(newMaint);
          }
        }
      }

      /*
       * Get the credential_store_supported field only from the stack definition during creation.
       * Not possible to update the value of credential_store_supported through a request.
       */

      /*
       * Gather the credential_store_enabled field per service.
       */
      if (StringUtils.isNotEmpty(request.getCredentialStoreEnabled())) {
        boolean credentialStoreEnabled = Boolean.parseBoolean(request.getCredentialStoreEnabled());
        if (!s.isCredentialStoreSupported() && credentialStoreEnabled) {
          throw new IllegalArgumentException("Invalid arguments, cannot enable credential store " +
              "as it is not supported by the service. Service=" + s.getName());
        }
        if (s.isCredentialStoreRequired() && !credentialStoreEnabled) {
          throw new IllegalArgumentException("Invalid arguments, cannot disable credential store " +
                                             "as it is required by the service. Service=" + s.getName());
        }
        serviceCredentialStoreEnabledMap.put(s, credentialStoreEnabled);
        LOG.info("Service: service_name = {}, service_type = {}, credential_store_enabled from request: {}",
          request.getServiceName(), request.getServiceType(), credentialStoreEnabled);
      }

      if (StringUtils.isNotEmpty(request.getCredentialStoreSupported())) {
        throw new IllegalArgumentException("Invalid arguments, cannot update credential_store_supported " +
                                           "as it is set only via service definition. Service=" + s.getName());
      }

      if (newState == null) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("Nothing to do for new updateService request, clusterId={}, serviceName={}, newDesiredState=null",
            request.getClusterName(), request.getServiceName());
        }
        continue;
      }

      if (! maintenanceStateHelper.isOperationAllowed(reqOpLvl, s)) {
        LOG.info("Operations cannot be applied to service " + s.getName() +
            " in the maintenance state of " + s.getMaintenanceState());
        continue;
      }

      seenNewStates.add(newState);

      if (newState != oldState) {
        // The if user is trying to start or stop the service, ensure authorization
        if (((newState == State.INSTALLED) || (newState == State.STARTED)) &&
            !AuthorizationHelper.isAuthorized(ResourceType.CLUSTER, cluster.getResourceId(), RoleAuthorization.SERVICE_START_STOP)) {
          throw new AuthorizationException("The authenticated user is not authorized to start or stop services");
        }

        if (!State.isValidDesiredStateTransition(oldState, newState)) {
          throw new AmbariException("Invalid transition for"
              + " service"
              + ", clusterName=" + cluster.getClusterName()
              + ", clusterId=" + cluster.getClusterId()
              + ", serviceName=" + s.getName()
              + ", currentDesiredState=" + oldState
              + ", newDesiredState=" + newState);

        }
        if (!changedServices.containsKey(newState)) {
          changedServices.put(newState, new ArrayList<>());
        }
        changedServices.get(newState).add(s);
      }

      // TODO should we check whether all servicecomponents and
      // servicecomponenthosts are in the required desired state?

      updateServiceComponents(requestStages, changedComps, changedScHosts,
        ignoredScHosts, reqOpLvl, s, newState);
    }

    if (startDependencies && changedServices.containsKey(State.STARTED)) {
      HashSet<Service> depServices = new HashSet<>();
      for (Service service : changedServices.get(State.STARTED)) {
        RoleCommandOrder rco = controller.getRoleCommandOrder(service.getCluster());
        Set<Service> dependencies = rco.getTransitiveServices(service, RoleCommand.START);
        for (Service dependency: dependencies) {
          if (!changedServices.get(State.STARTED).contains(dependency)){
            depServices.add(dependency);
          }
        }
      }

      for (Service service : depServices) {
        updateServiceComponents(requestStages, changedComps, changedScHosts,
          ignoredScHosts, reqOpLvl, service, State.STARTED);
        changedServices.get(State.STARTED).add(service);
      }

    }

    if (seenNewStates.size() > 1) {
      // TODO should we handle this scenario
      throw new IllegalArgumentException("Cannot handle different desired state"
          + " changes for a set of services at the same time");
    }

    // update the credential store enabled information
    for (Map.Entry<Service, Boolean> serviceCredential : serviceCredentialStoreEnabledMap.entrySet()) {
      Service service = serviceCredential.getKey();
      Boolean credentialStoreEnabled = serviceCredential.getValue();
      service.setCredentialStoreEnabled(credentialStoreEnabled);
    }


    Cluster cluster = clusters.getCluster(clusterIds.iterator().next());

    return controller.addStages(requestStages, cluster, requestProperties,
      null, changedServices, changedComps, changedScHosts,
        ignoredScHosts, runSmokeTest, reconfigureClients);
  }
public Set<ServiceComponentResponse> createComponents(Set<ServiceComponentRequest> requests)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return null;
    }

    Set<ServiceComponentResponse> createdSvcCmpnt = new HashSet<>();
    Clusters clusters = getManagementController().getClusters();
    AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();
    ServiceComponentFactory serviceComponentFactory = getManagementController().getServiceComponentFactory();

    // do all validation checks
    Map<String, Map<String, Set<String>>> componentNames = new HashMap<>();
    Set<String> duplicates = new HashSet<>();

    for (ServiceComponentRequest request : requests) {
      Validate.notEmpty(request.getComponentName(), "component name should be non-empty");
      Validate.notEmpty(request.getServiceGroupName(), "service group name should be non-empty");
      Validate.notEmpty(request.getServiceName(), "service name should be non-empty");
      Cluster cluster = getClusterForRequest(request, clusters);

      isAuthorized(cluster, getRequiredCreateAuthorizations());

      debug("Received a createComponent request: {}", request);

      if (!componentNames.containsKey(request.getClusterName())) {
        componentNames.put(request.getClusterName(), new HashMap<>());
      }

      Map<String, Set<String>> serviceComponents = componentNames.get(request.getClusterName());
      if (!serviceComponents.containsKey(request.getServiceName())) {
        serviceComponents.put(request.getServiceName(), new HashSet<String>());
      }

      if (serviceComponents.get(request.getServiceName()).contains(request.getComponentName())) {
        // throw error later for dup
        duplicates.add(request.toString());
        continue;
      }
      serviceComponents.get(request.getServiceName()).add(request.getComponentName());

      if (StringUtils.isNotEmpty(request.getDesiredState())) {
        Validate.isTrue(State.INIT == State.valueOf(request.getDesiredState()),
            "Invalid desired state only INIT state allowed during creation, providedDesiredState=" + request.getDesiredState());
      }

      Service s = getServiceFromCluster(request, cluster);

      try {
        ServiceComponent sc = s.getServiceComponent(request.getComponentName());
        if (sc != null) {
          // throw error later for dup
          duplicates.add(request.toString());
          continue;
        }
      } catch (AmbariException e) {
        // Expected
      }

      StackId stackId = s.getDesiredStackId();
      if (!ambariMetaInfo.isValidServiceComponent(stackId.getStackName(),
          stackId.getStackVersion(), s.getServiceType(), request.getComponentName())) {
        throw new IllegalArgumentException("Unsupported or invalid component"
            + " in stack stackInfo=" + stackId.getStackId()
            + " request=" + request);
      }
    }

    // ensure only a single cluster update
    Validate.isTrue(componentNames.size() == 1,
        "Invalid arguments, updates allowed on only one cluster at a time");

    // Validate dups
    if (!duplicates.isEmpty()) {
      //Java8 has StringJoiner library but ambari is not on Java8 yet.
      throw new DuplicateResourceException("Attempted to create one or more components which already exist:"
                            + StringUtils.join(duplicates, ","));
    }

    // now doing actual work
    for (ServiceComponentRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());
      Service s = cluster.getService(request.getServiceName());
      ServiceComponent sc = serviceComponentFactory.createNew(s, request.getComponentName());
      sc.setDesiredRepositoryVersion(s.getDesiredRepositoryVersion());

      if (StringUtils.isNotEmpty(request.getDesiredState())) {
        State state = State.valueOf(request.getDesiredState());
        sc.setDesiredState(state);
      } else {
        sc.setDesiredState(s.getDesiredState());
      }

      /*
       * If request does not have recovery_enabled field,
       * then get the default from the stack definition.
       */
      if (StringUtils.isNotEmpty(request.getRecoveryEnabled())) {
        boolean recoveryEnabled = Boolean.parseBoolean(request.getRecoveryEnabled());
        sc.setRecoveryEnabled(recoveryEnabled);
        LOG.info("Component: {}, recovery_enabled from request: {}", request.getComponentName(), recoveryEnabled);
      } else {
        StackId stackId = s.getDesiredStackId();
        ComponentInfo componentInfo = ambariMetaInfo.getComponent(stackId.getStackName(),
                stackId.getStackVersion(), s.getServiceType(), request.getComponentName());
        if (componentInfo == null) {
            throw new AmbariException("Could not get component information from stack definition: Stack=" +
              stackId + ", Service=" + s.getServiceType() + ", Component=" + request.getComponentName());
        }
        sc.setRecoveryEnabled(componentInfo.isRecoveryEnabled());
        LOG.info("Component: {}, recovery_enabled from stack definition:{}", componentInfo.getName(),
                componentInfo.isRecoveryEnabled());
      }

      s.addServiceComponent(sc);
      createdSvcCmpnt.add(sc.convertToResponse());
    }
    return createdSvcCmpnt;
  }
protected synchronized void createComponents(
      Set<ServiceComponentRequest> requests) throws AmbariException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return;
    }

    Clusters clusters = getManagementController().getClusters();
    AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();
    ServiceComponentFactory serviceComponentFactory = getManagementController().getServiceComponentFactory();

    // do all validation checks
    Map<String, Map<String, Set<String>>> componentNames =
        new HashMap<String, Map<String, Set<String>>>();
    Set<String> duplicates = new HashSet<String>();

    for (ServiceComponentRequest request : requests) {
      if (request.getClusterName() == null
          || request.getClusterName().isEmpty()
          || request.getComponentName() == null
          || request.getComponentName().isEmpty()) {
        throw new IllegalArgumentException("Invalid arguments"
            + ", clustername and componentname should be"
            + " non-null and non-empty when trying to create a"
            + " component");
      }

      Cluster cluster;
      try {
        cluster = clusters.getCluster(request.getClusterName());
      } catch (ClusterNotFoundException e) {
        throw new ParentObjectNotFoundException(
            "Attempted to add a component to a cluster which doesn't exist:", e);
      }

      if (request.getServiceName() == null
          || request.getServiceName().isEmpty()) {
        StackId stackId = cluster.getDesiredStackVersion();
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
          throw new AmbariException("Could not find service for component"
              + ", componentName=" + request.getComponentName()
              + ", clusterName=" + cluster.getClusterName()
              + ", stackInfo=" + stackId.getStackId());
        }
        request.setServiceName(serviceName);
      }

      if (LOG.isDebugEnabled()) {
        LOG.debug("Received a createComponent request"
            + ", clusterName=" + request.getClusterName()
            + ", serviceName=" + request.getServiceName()
            + ", componentName=" + request.getComponentName()
            + ", request=" + request);
      }

      if (!componentNames.containsKey(request.getClusterName())) {
        componentNames.put(request.getClusterName(),
            new HashMap<String, Set<String>>());
      }
      if (!componentNames.get(request.getClusterName())
          .containsKey(request.getServiceName())) {
        componentNames.get(request.getClusterName()).put(
            request.getServiceName(), new HashSet<String>());
      }
      if (componentNames.get(request.getClusterName())
          .get(request.getServiceName()).contains(request.getComponentName())) {
        // throw error later for dup
        duplicates.add("[clusterName=" + request.getClusterName() + ", serviceName=" + request.getServiceName() +
            ", componentName=" + request.getComponentName() + "]");
        continue;
      }
      componentNames.get(request.getClusterName())
          .get(request.getServiceName()).add(request.getComponentName());

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

      Service s;
      try {
        s = cluster.getService(request.getServiceName());
      } catch (ServiceNotFoundException e) {
        throw new ParentObjectNotFoundException(
            "Attempted to add a component to a service which doesn't exist:", e);
      }
      try {
        ServiceComponent sc = s.getServiceComponent(request.getComponentName());
        if (sc != null) {
          // throw error later for dup
          duplicates.add("[clusterName=" + request.getClusterName() + ", serviceName=" + request.getServiceName() +
              ", componentName=" + request.getComponentName() + "]");
          continue;
        }
      } catch (AmbariException e) {
        // Expected
      }

      StackId stackId = s.getDesiredStackVersion();
      if (!ambariMetaInfo.isValidServiceComponent(stackId.getStackName(),
          stackId.getStackVersion(), s.getName(), request.getComponentName())) {
        throw new IllegalArgumentException("Unsupported or invalid component"
            + " in stack"
            + ", clusterName=" + request.getClusterName()
            + ", serviceName=" + request.getServiceName()
            + ", componentName=" + request.getComponentName()
            + ", stackInfo=" + stackId.getStackId());
      }
    }

    // ensure only a single cluster update
    if (componentNames.size() != 1) {
      throw new IllegalArgumentException("Invalid arguments, updates allowed"
          + "on only one cluster at a time");
    }

    // Validate dups
    if (!duplicates.isEmpty()) {
      StringBuilder names = new StringBuilder();
      boolean first = true;
      for (String cName : duplicates) {
        if (!first) {
          names.append(",");
        }
        first = false;
        names.append(cName);
      }
      String msg;
      if (duplicates.size() == 1) {
        msg = "Attempted to create a component which already exists: ";
      } else {
        msg = "Attempted to create components which already exist: ";
      }
      throw new DuplicateResourceException(msg + names.toString());
    }

    // now doing actual work
    for (ServiceComponentRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());
      Service s = cluster.getService(request.getServiceName());
      ServiceComponent sc = serviceComponentFactory.createNew(s,
          request.getComponentName());
      sc.setDesiredStackVersion(s.getDesiredStackVersion());

      if (request.getDesiredState() != null
          && !request.getDesiredState().isEmpty()) {
        State state = State.valueOf(request.getDesiredState());
        sc.setDesiredState(state);
      } else {
        sc.setDesiredState(s.getDesiredState());
      }

      s.addServiceComponent(sc);
      sc.persist();
    }
  }
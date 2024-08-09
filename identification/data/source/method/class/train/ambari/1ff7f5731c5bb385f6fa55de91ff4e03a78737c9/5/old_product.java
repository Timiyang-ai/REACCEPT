private Set<ServiceComponentResponse> getComponents(ServiceComponentRequest request) throws AmbariException {

    final AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();
    final Clusters clusters = getManagementController().getClusters();
    final Cluster cluster = getCluster(request, clusters);

    Set<ServiceComponentResponse> response = new HashSet<>();
    String category = null;


    if (request.getComponentName() != null) {
      setServiceNameIfAbsent(request, cluster, ambariMetaInfo);

      final Service s = getServiceFromCluster(request, cluster);
      ServiceComponent sc = s.getServiceComponent(request.getComponentName());
      ServiceComponentResponse serviceComponentResponse = sc.convertToResponse();
      StackId stackId = sc.getDesiredStackId();

      ServiceGroup sg = null;
      try {
        sg = cluster.getServiceGroup(sc.getServiceGroupId());
      } catch (ServiceGroupNotFoundException e) {
        sg = null;
      }

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

    Set<Service> services = new HashSet<>();
    if (StringUtils.isNotEmpty(request.getServiceDisplayName())) {
      services.add(getServiceFromCluster(request, cluster));
    } else {
      services.addAll(cluster.getServices().values());
    }

    final State desiredStateToCheck = getValidDesiredState(request);
    for (Service s : services) {
      // filter on request.getDesiredState()
      for (ServiceComponent sc : s.getServiceComponents().values()) {
        if (desiredStateToCheck != null && desiredStateToCheck != sc.getDesiredState()) {
          // skip non matching state
          continue;
        }

        StackId stackId = sc.getDesiredStackId();

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
        if (StringUtils.isNotEmpty(requestedCategory) && !requestedCategory.equalsIgnoreCase(category)) {
          continue;
        }

        response.add(serviceComponentResponse);
      }
    }
    return response;
  }
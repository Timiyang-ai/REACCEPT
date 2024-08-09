AddServiceInfo recommendLayout(AddServiceInfo info) {
    try {
      Cluster cluster = managementController.getClusters().getCluster(info.clusterName());
      Map<String, Map<String, Set<String>>> clusterServices = transformValues(
        cluster.getServices(),
        service -> transformValues(service.getServiceComponents(), component -> component.getServiceComponentsHosts()));

      // Requested component layout will be added to the StackAdvisor input in addition to existing
      // component layout.
      Map<String, Map<String, Set<String>>> allServices = mergeDisjunctMaps(clusterServices, info.newServices());

      Map<String, Set<String>> componentsToHosts = getComponentHostMap(allServices);

      Map<String, Set<String>> hostsToComponents = getHostComponentMap(componentsToHosts);
      List<String> hosts = ImmutableList.copyOf(cluster.getHostNames());
      hosts.forEach( host -> hostsToComponents.putIfAbsent(host, new HashSet<>())); // just in case there are hosts that have no components

      Map<String, Set<String>> hostGroups = getHostGroupStrategy().calculateHostGroups(hostsToComponents);

      StackAdvisorRequest request = StackAdvisorRequest.StackAdvisorRequestBuilder
        .forStack(info.getStack().getStackId())
        .ofType(StackAdvisorRequest.StackAdvisorRequestType.HOST_GROUPS)
        .forHosts(hosts)
        .forServices(allServices.keySet())
        .forHostComponents(hostsToComponents)
        .forHostsGroupBindings(hostGroups)
        .withComponentHostsMap(componentsToHosts)
        .withGPLLicenseAccepted(serverConfig.getGplLicenseAccepted())
        .build();
      RecommendationResponse response = stackAdvisorHelper.recommend(request);

      Map<String, Map<String, Set<String>>> recommendedLayout = getRecommendedLayout(
        response.getRecommendations().getBlueprintClusterBinding().getHostgroupHostMap(),
        response.getRecommendations().getBlueprint().getHostgroupComponentMap(),
        info.getStack()::getServiceForComponent);

      Set<ValidationResponse.ValidationItem> validationItems = validateRecommendedLayout(info.getStack().getStackId(),
        recommendedLayout,
        response.getRecommendations().getBlueprintClusterBinding().getHostgroupHostMap());
      if (!validationItems.isEmpty()) {
        LOG.warn("Issues found during recommended topology validation:\n{}", Joiner.on('\n').join(validationItems));
      }

      // Keep the recommendations for new services only
      keepNewServicesOnly(recommendedLayout, info.newServices());

      return info.withNewServices(recommendedLayout);
    }
    catch (AmbariException|StackAdvisorException ex) {
      throw new IllegalArgumentException("Layout recommendation failed.", ex);
    }
  }
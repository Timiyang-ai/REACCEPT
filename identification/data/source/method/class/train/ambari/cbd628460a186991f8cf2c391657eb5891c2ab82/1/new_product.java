AddServiceInfo recommendLayout(AddServiceInfo info) {
    try {
      // Requested component layout will be added to the StackAdvisor input in addition to existing
      // component layout.
      Map<String, Map<String, Set<String>>> allServices = getAllServices(info);

      Map<String, Set<String>> componentsToHosts = getComponentHostMap(allServices);

      Map<String, Set<String>> hostsToComponents = getHostComponentMap(componentsToHosts);
      List<String> hosts = ImmutableList.copyOf(getCluster(info).getHostNames());
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
        .withConfigurations(info.getConfig())
        .withGPLLicenseAccepted(serverConfig.getGplLicenseAccepted())
        .build();
      RecommendationResponse response = stackAdvisorHelper.recommend(request);

      Map<String, Map<String, Set<String>>> recommendedLayout = getRecommendedLayout(
        response.getRecommendations().getBlueprintClusterBinding().getHostgroupHostMap(),
        response.getRecommendations().getBlueprint().getHostgroupComponentMap(),
        info.getStack()::getServiceForComponent);

      // Validate layout
      Map<String, Set<String>> recommendedComponentHosts = getComponentHostMap(recommendedLayout);
      StackAdvisorRequest validationRequest = request.builder()
        .forHostsGroupBindings(response.getRecommendations().getBlueprintClusterBinding().getHostgroupHostMap())
        .withComponentHostsMap(recommendedComponentHosts)
        .forHostComponents(getHostComponentMap(recommendedComponentHosts)).build();
      validate(validationRequest);

      Map<String,Map<String,Set<String>>> newServiceRecommendations = keepNewServicesOnly(recommendedLayout, info.newServices());
      LayoutRecommendationInfo recommendationInfo = new LayoutRecommendationInfo(
        response.getRecommendations().getBlueprintClusterBinding().getHostgroupHostMap(),
        recommendedLayout);
      return info.withLayoutRecommendation(newServiceRecommendations, recommendationInfo);
    }
    catch (AmbariException|StackAdvisorException ex) {
      throw new IllegalArgumentException("Layout recommendation failed.", ex);
    }
  }
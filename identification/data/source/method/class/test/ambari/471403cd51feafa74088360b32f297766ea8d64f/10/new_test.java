  public static RequestStatusResponse updateComponents(AmbariManagementController controller,
                                                     Set<ServiceComponentRequest> requests,
                                                     Map<String, String> requestProperties, boolean runSmokeTest)
      throws AmbariException, AuthorizationException {
    ComponentResourceProvider provider = getComponentResourceProvider(controller);
    return provider.updateComponents(requests, requestProperties, runSmokeTest);
  }
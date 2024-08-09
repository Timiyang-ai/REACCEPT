  public static RequestStatusResponse deleteServices(AmbariManagementController controller, Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException, NoSuchFieldException, IllegalAccessException {
    ServiceResourceProvider provider = getServiceProvider(controller);
    return provider.deleteServices(requests);
  }
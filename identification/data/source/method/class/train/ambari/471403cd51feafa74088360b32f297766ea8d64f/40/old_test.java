  public static Set<ServiceResponse> getServices(AmbariManagementController controller,
                                                 Set<ServiceRequest> requests)
      throws AmbariException, NoSuchFieldException, IllegalAccessException {
    ServiceResourceProvider provider = getServiceProvider(controller);
    return provider.getServices(requests);
  }
  public static void createComponents(AmbariManagementController controller, Set<ServiceComponentRequest> requests)
      throws AmbariException, AuthorizationException {
    ComponentResourceProvider provider = getComponentResourceProvider(controller);
    provider.createComponents(requests);
  }
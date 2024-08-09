  public static Set<ServiceComponentResponse> getComponents(AmbariManagementController controller,
                                                 Set<ServiceComponentRequest> requests) throws AmbariException {
    ComponentResourceProvider provider = getComponentResourceProvider(controller);
    return provider.getComponents(requests);
  }
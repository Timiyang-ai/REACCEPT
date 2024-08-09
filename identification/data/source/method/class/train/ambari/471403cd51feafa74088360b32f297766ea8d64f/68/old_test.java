  public static void createServices(AmbariManagementController controller,
      RepositoryVersionDAO repositoryVersionDAO, Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException, NoSuchFieldException, IllegalAccessException {
    MaintenanceStateHelper maintenanceStateHelperMock = createNiceMock(MaintenanceStateHelper.class);
    ServiceResourceProvider provider = getServiceProvider(controller, maintenanceStateHelperMock, repositoryVersionDAO);
    provider.createServices(requests);
  }
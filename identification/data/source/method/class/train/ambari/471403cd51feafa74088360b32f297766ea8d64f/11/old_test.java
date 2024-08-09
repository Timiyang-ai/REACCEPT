  public static RequestStatusResponse updateServices(AmbariManagementController controller,
                                                     Set<ServiceRequest> requests,
                                                     Map<String, String> requestProperties, boolean runSmokeTest,
                                                     boolean reconfigureClients)
      throws AmbariException, AuthorizationException, NoSuchFieldException, IllegalAccessException {
    return updateServices(controller, requests, requestProperties, runSmokeTest, reconfigureClients, null);
  }
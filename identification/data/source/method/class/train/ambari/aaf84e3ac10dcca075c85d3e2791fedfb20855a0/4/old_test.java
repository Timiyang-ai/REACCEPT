  public static RequestStatusResponse updateHostComponents(AmbariManagementController controller,
                                                           Injector injector,
                                                           Set<ServiceComponentHostRequest> requests,
                                                           Map<String, String> requestProperties,
                                                           boolean runSmokeTest) throws Exception {
    Resource.Type type = Resource.Type.HostComponent;
    TestHostComponentResourceProvider provider =
        new TestHostComponentResourceProvider(PropertyHelper.getPropertyIds(type),
            PropertyHelper.getKeyPropertyIds(type),
            controller, injector);

    provider.setFieldValue("maintenanceStateHelper", injector.getInstance(MaintenanceStateHelper.class));
    provider.setFieldValue("hostVersionDAO", injector.getInstance(HostVersionDAO.class));

    RequestStageContainer requestStages = provider.updateHostComponents(null, requests, requestProperties,
        runSmokeTest, false, false);
    requestStages.persist();
    return requestStages.getRequestStatusResponse();
  }
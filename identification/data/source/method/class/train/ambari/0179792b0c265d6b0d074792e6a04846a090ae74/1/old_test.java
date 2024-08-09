  private RequestStatusResponse updateHostComponents(Set<ServiceComponentHostRequest> requests,
                                                     Map<String, String> requestProperties,
                                                     boolean runSmokeTest) throws Exception {

    return updateHostComponents(controller, requests, requestProperties, runSmokeTest);
  }
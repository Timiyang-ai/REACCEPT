  private ServiceInstances getServiceInstances(Id.Service serviceId) throws Exception {
    String instanceUrl = String.format("apps/%s/services/%s/instances", serviceId.getApplicationId(),
                                       serviceId.getId());
    String versionedInstanceUrl = getVersionedAPIPath(instanceUrl, Constants.Gateway.API_VERSION_3_TOKEN,
                                                      serviceId.getNamespaceId());
    HttpResponse response = doGet(versionedInstanceUrl);
    Assert.assertEquals(200, response.getResponseCode());
    return readResponse(response, ServiceInstances.class);
  }
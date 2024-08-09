  private HttpResponse getServiceAvailability(Id.Service serviceId) throws Exception {
    String activeUrl = String.format("apps/%s/services/%s/available", serviceId.getApplicationId(), serviceId.getId());
    String versionedActiveUrl = getVersionedAPIPath(activeUrl, Constants.Gateway.API_VERSION_3_TOKEN,
                                                    serviceId.getNamespaceId());
    return doGet(versionedActiveUrl);
  }
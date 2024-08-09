@PUT
  @Path("/apps/{app-id}/services/{service-id}/instances")
  public void setServiceInstances(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId,
                                  @PathParam("service-id") String serviceId) {
    setServiceInstances(request, responder, namespaceId, appId, serviceId, serviceId);
  }
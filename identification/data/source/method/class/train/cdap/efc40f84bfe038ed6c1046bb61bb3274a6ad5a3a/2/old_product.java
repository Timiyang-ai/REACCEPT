@GET
  @Path("/apps/{app-name}/services/{service-name}/available")
  public void getServiceAvailability(HttpRequest request, HttpResponder responder,
                                     @PathParam("namespace-id") String namespaceId,
                                     @PathParam("app-name") String appName,
                                     @PathParam("service-name") String serviceName) throws Exception {
    getServiceAvailability(request, responder, namespaceId, appName, ApplicationId.DEFAULT_VERSION, serviceName);
  }
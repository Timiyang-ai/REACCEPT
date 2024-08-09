@GET
  @Path("/apps/{app-name}/{service-type}/{program-name}/available")
  public void getServiceAvailability(HttpRequest request, HttpResponder responder,
                                     @PathParam("namespace-id") String namespaceId,
                                     @PathParam("app-name") String appName,
                                     @PathParam("service-type") String serviceType,
                                     @PathParam("program-name") String programName) throws Exception {
    getServiceAvailability(request, responder, namespaceId, appName,
                           ApplicationId.DEFAULT_VERSION, serviceType, programName);
  }
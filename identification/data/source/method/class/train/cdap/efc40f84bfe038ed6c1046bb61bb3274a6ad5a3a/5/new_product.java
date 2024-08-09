@GET
  @Path("/apps/{app-id}/services/{service-id}/available")
  public void getServiceAvailability(HttpRequest request, HttpResponder responder,
                                     @PathParam("namespace-id") String namespaceId,
                                     @PathParam("app-id") String appId,
                                     @PathParam("service-id") String serviceId) throws Exception {
    ProgramId programId = new ProgramId(namespaceId, appId, ProgramType.SERVICE, serviceId);
    ProgramStatus status = lifecycleService.getProgramStatus(programId);
    if (status == ProgramStatus.STOPPED) {
      responder.sendString(HttpResponseStatus.SERVICE_UNAVAILABLE, "Service is stopped. Please start it.");
    } else {
      // Construct discoverable name and return 200 OK if discoverable is present. If not return 503.
      String serviceName = ServiceDiscoverable.getName(programId);
      EndpointStrategy endpointStrategy = new RandomEndpointStrategy(discoveryServiceClient.discover(serviceName));
      if (endpointStrategy.pick(300L, TimeUnit.MILLISECONDS) == null) {
        LOG.trace("Discoverable endpoint {} not found", serviceName);
        responder.sendString(HttpResponseStatus.SERVICE_UNAVAILABLE,
                             "Service is running but not accepting requests at this time.");
      } else {
        responder.sendString(HttpResponseStatus.OK, "Service is available to accept requests.");
      }
    }
  }
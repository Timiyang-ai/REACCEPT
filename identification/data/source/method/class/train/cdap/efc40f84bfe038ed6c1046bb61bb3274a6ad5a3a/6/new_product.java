@GET
  @Path("/apps/{app-name}/versions/{app-version}/services/{service-name}/available")
  public void getServiceAvailability(HttpRequest request, HttpResponder responder,
                                     @PathParam("namespace-id") String namespaceId,
                                     @PathParam("app-name") String appName,
                                     @PathParam("app-version") String appVersion,
                                     @PathParam("service-name") String serviceName) throws Exception {
    ServiceId serviceId = new ApplicationId(namespaceId, appName, appVersion).service(serviceName);
    ProgramStatus status = lifecycleService.getProgramStatus(serviceId);
    if (status == ProgramStatus.STOPPED) {
      responder.sendString(HttpResponseStatus.SERVICE_UNAVAILABLE, "Service is stopped. Please start it.");
    } else {
      // Construct discoverable name and return 200 OK if discoverable is present. If not return 503.
      String serviceDiscoverableName = ServiceDiscoverable.getName(serviceId);
      EndpointStrategy endpointStrategy =
        new RandomEndpointStrategy(discoveryServiceClient.discover(serviceDiscoverableName));
      if (endpointStrategy.pick(300L, TimeUnit.MILLISECONDS) == null) {
        LOG.trace("Discoverable endpoint {} not found", serviceDiscoverableName);
        responder.sendString(HttpResponseStatus.SERVICE_UNAVAILABLE,
                             "Service is running but not accepting requests at this time.");
      } else {
        responder.sendString(HttpResponseStatus.OK, "Service is available to accept requests.");
      }
    }
  }
@GET
  @Path("/apps/{app-name}/versions/{app-version}/{service-type}/{program-name}/available")
  public void getServiceAvailability(HttpRequest request, HttpResponder responder,
                                     @PathParam("namespace-id") String namespaceId,
                                     @PathParam("app-name") String appName,
                                     @PathParam("app-version") String appVersion,
                                     @PathParam("service-type") String serviceType,
                                     @PathParam("program-name") String programName) throws Exception {
    // Currently we only support services and sparks as the service-type
    ProgramType programType = ProgramType.valueOfCategoryName(serviceType);
    if (!ServiceDiscoverable.getUserServiceTypes().contains(programType)) {
      throw new BadRequestException("Only service or spark is support for service availability check");
    }

    ProgramId programId = new ProgramId(new ApplicationId(namespaceId, appName, appVersion), programType, programName);
    ProgramStatus status = lifecycleService.getProgramStatus(programId);
    if (status == ProgramStatus.STOPPED) {
      throw new ServiceUnavailableException(programId.toString(), "Service is stopped. Please start it.");
    }

    // Construct discoverable name and return 200 OK if discoverable is present. If not return 503.
    String discoverableName = ServiceDiscoverable.getName(programId);

    // TODO: CDAP-12959 - Should use the UserServiceEndpointStrategy and discover based on the version
    // and have appVersion nullable for the non versioned endpoint
    EndpointStrategy endpointStrategy = new RandomEndpointStrategy(discoveryServiceClient.discover(discoverableName));
    if (endpointStrategy.pick(300L, TimeUnit.MILLISECONDS) == null) {
      LOG.trace("Discoverable endpoint {} not found", discoverableName);
      throw new ServiceUnavailableException(programId.toString(),
                                            "Service is running but not accepting requests at this time.");
    }

    responder.sendString(HttpResponseStatus.OK, "Service is available to accept requests.");
  }
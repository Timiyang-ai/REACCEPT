@GET
  @Path("/apps/{app-id}/services/{service-id}/instances")
  public void getServiceInstances(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId,
                                  @PathParam("service-id") String serviceId)  {
    try {
      ProgramId programId = Ids.namespace(namespaceId).app(appId).service(serviceId);
      if (!store.programExists(programId.toId())) {
        responder.sendString(HttpResponseStatus.NOT_FOUND, "Service not found");
        return;
      }

      ServiceSpecification specification = (ServiceSpecification) lifecycleService.getProgramSpecification(programId);
      if (specification == null) {
        responder.sendStatus(HttpResponseStatus.NOT_FOUND);
        return;
      }

      int instances = specification.getInstances();
      responder.sendJson(HttpResponseStatus.OK,
                         new ServiceInstances(instances, getInstanceCount(programId, serviceId)));
    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    }
  }
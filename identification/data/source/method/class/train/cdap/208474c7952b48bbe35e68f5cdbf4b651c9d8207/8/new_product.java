@GET
  @Path("/apps/{app-id}/services/{service-id}/instances")
  public void getServiceInstances(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId,
                                  @PathParam("service-id") String serviceId) throws Exception {
    try {
      ProgramId programId = validateAndGetNamespace(namespaceId).app(appId).service(serviceId);
      if (!lifecycleService.programExists(programId)) {
        throw new NotFoundException(programId);
      }

      int instances = store.getServiceInstances(programId);
      responder.sendJson(HttpResponseStatus.OK,
                         GSON.toJson(new ServiceInstances(instances, getInstanceCount(programId, serviceId))));
    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    }
  }
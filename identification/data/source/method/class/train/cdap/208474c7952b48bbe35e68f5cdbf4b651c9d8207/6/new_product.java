@GET
  @Path("/apps/{app-id}/services/{service-id}/instances")
  public void getServiceInstances(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId,
                                  @PathParam("service-id") String serviceId)  {
    try {
      Id.Program programId = Id.Program.from(namespaceId, appId, ProgramType.SERVICE, serviceId);
      if (!store.programExists(programId, ProgramType.SERVICE)) {
        responder.sendString(HttpResponseStatus.NOT_FOUND, "Service not found");
        return;
      }

      ServiceSpecification specification = (ServiceSpecification) getProgramSpecification(programId,
                                                                                          ProgramType.SERVICE);
      if (specification == null) {
        responder.sendStatus(HttpResponseStatus.NOT_FOUND);
        return;
      }

      // If the runnable name is the same as the service name, then uses the service spec, otherwise use the worker spec
      int instances = specification.getInstances();
      responder.sendJson(HttpResponseStatus.OK, new ServiceInstances(
        instances, getInstanceCount(namespaceId, appId, ProgramType.SERVICE, serviceId, serviceId)));

    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    } catch (Throwable e) {
      LOG.error("Got exception:", e);
      responder.sendStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
  }
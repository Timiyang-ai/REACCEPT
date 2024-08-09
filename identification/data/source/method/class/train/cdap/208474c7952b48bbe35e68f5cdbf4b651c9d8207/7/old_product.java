@GET
  @Path("/apps/{app-id}/services/{service-id}/runnables/{runnable-name}/instances")
  public void getServiceInstances(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId,
                                  @PathParam("service-id") String serviceId,
                                  @PathParam("runnable-name") String runnableName) {
    try {
      Id.Program programId = Id.Program.from(namespaceId, appId, serviceId);
      if (!store.programExists(programId, ProgramType.SERVICE)) {
        responder.sendString(HttpResponseStatus.NOT_FOUND, "Runnable not found");
        return;
      }

      ServiceSpecification specification = (ServiceSpecification) getProgramSpecification(programId,
                                                                                          ProgramType.SERVICE);
      if (specification == null) {
        responder.sendStatus(HttpResponseStatus.NOT_FOUND);
        return;
      }

      // If the runnable name is the same as the service name, then uses the service spec, otherwise use the worker spec
      int instances;
      if (specification.getName().equals(runnableName)) {
        instances = specification.getInstances();
      } else {
        ServiceWorkerSpecification workerSpec = specification.getWorkers().get(runnableName);
        if (workerSpec == null) {
          responder.sendStatus(HttpResponseStatus.NOT_FOUND);
          return;
        }
        instances = workerSpec.getInstances();
      }

      responder.sendJson(HttpResponseStatus.OK,
                         new ServiceInstances(instances, getRunnableCount(namespaceId, appId, ProgramType.SERVICE,
                                                                          serviceId, runnableName)));

    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    } catch (Throwable e) {
      LOG.error("Got exception:", e);
      responder.sendStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
  }
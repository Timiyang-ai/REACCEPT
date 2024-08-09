void getServiceInstances(HttpRequest request, HttpResponder responder,
                           String namespaceId, String appId, String serviceId, String runnableName) {
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
                         new ServiceInstances(instances, getInstanceCount(namespaceId, appId, ProgramType.SERVICE,
                                                                          serviceId, runnableName)));

    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    } catch (Throwable e) {
      LOG.error("Got exception:", e);
      responder.sendStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
  }
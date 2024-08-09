@PUT
  @Path("/apps/{app-id}/services/{service-id}/runnables/{runnable-name}/instances")
  public void setServiceInstances(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId,
                                  @PathParam("service-id") String serviceId,
                                  @PathParam("runnable-name") String runnableName) {

    try {
      Id.Program programId = Id.Program.from(namespaceId, appId, serviceId);
      if (!store.programExists(programId, ProgramType.SERVICE)) {
        responder.sendString(HttpResponseStatus.NOT_FOUND, "Service not found");
        return;
      }

      int instances = getInstances(request);
      if (instances < 1) {
        responder.sendString(HttpResponseStatus.BAD_REQUEST, "Instance count should be greater than 0");
        return;
      }

      // If the runnable name is the same as the service name, it's setting the service instances
      // TODO: This REST API is bad, need to update (CDAP-388)
      int oldInstances = (runnableName.equals(serviceId)) ? store.getServiceInstances(programId)
        : store.getServiceWorkerInstances(programId, runnableName);
      if (oldInstances != instances) {
        if (runnableName.equals(serviceId)) {
          store.setServiceInstances(programId, instances);
        } else {
          store.setServiceWorkerInstances(programId, runnableName, instances);
        }

        ProgramRuntimeService.RuntimeInfo runtimeInfo = findRuntimeInfo(programId.getNamespaceId(),
                                                                        programId.getApplicationId(),
                                                                        programId.getId(),
                                                                        ProgramType.SERVICE, runtimeService);
        if (runtimeInfo != null) {
          runtimeInfo.getController().command(ProgramOptionConstants.INSTANCES,
                                              ImmutableMap.of(runnableName, String.valueOf(instances))).get();
        }
      }
      responder.sendStatus(HttpResponseStatus.OK);
    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    } catch (Throwable throwable) {
      if (respondIfElementNotFound(throwable, responder)) {
        return;
      }
      LOG.error("Got exception : ", throwable);
      responder.sendStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
  }
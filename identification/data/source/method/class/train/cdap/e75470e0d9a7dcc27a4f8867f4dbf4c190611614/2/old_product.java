@PUT
  @Path("/apps/{app-id}/services/{service-id}/instances")
  public void setServiceInstances(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId,
                                  @PathParam("service-id") String serviceId) {
    try {
      Id.Program programId = Id.Program.from(namespaceId, appId, ProgramType.SERVICE, serviceId);
      if (!store.programExists(programId, ProgramType.SERVICE)) {
        responder.sendString(HttpResponseStatus.NOT_FOUND, "Service not found");
        return;
      }

      int instances;
      try {
        instances = getInstances(request);
      } catch (IllegalArgumentException e) {
        responder.sendString(HttpResponseStatus.BAD_REQUEST, "Invalid instance value in request");
        return;
      } catch (JsonSyntaxException e) {
        responder.sendString(HttpResponseStatus.BAD_REQUEST, "Invalid JSON in request");
        return;
      }
      if (instances < 1) {
        responder.sendString(HttpResponseStatus.BAD_REQUEST, "Instance count should be greater than 0");
        return;
      }

      int oldInstances = store.getServiceInstances(programId);
      if (oldInstances != instances) {
        store.setServiceInstances(programId, instances);
        ProgramRuntimeService.RuntimeInfo runtimeInfo = findRuntimeInfo(namespaceId, appId, serviceId,
                                                                        ProgramType.SERVICE, runtimeService);
        if (runtimeInfo != null) {
          runtimeInfo.getController().command(ProgramOptionConstants.INSTANCES,
                                              ImmutableMap.of(serviceId, String.valueOf(instances))).get();
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
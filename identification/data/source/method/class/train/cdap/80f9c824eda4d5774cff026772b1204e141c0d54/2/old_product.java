@GET
  @Path("/apps/{app-id}/workflows/{workflow-id}/nextruntime")
  public void getScheduledRunTime(HttpRequest request, HttpResponder responder,
                                  @PathParam("namespace-id") String namespaceId,
                                  @PathParam("app-id") String appId, @PathParam("workflow-id") String workflowId) {
    try {
      Id.Program id = Id.Program.from(namespaceId, appId, ProgramType.WORKFLOW, workflowId);
      List<ScheduledRuntime> runtimes = scheduler.nextScheduledRuntime(id, SchedulableProgramType.WORKFLOW);

      JsonArray array = new JsonArray();
      for (ScheduledRuntime runtime : runtimes) {
        JsonObject object = new JsonObject();
        object.addProperty("id", runtime.getScheduleId());
        object.addProperty("time", runtime.getTime());
        array.add(object);
      }
      responder.sendJson(HttpResponseStatus.OK, array);
    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    } catch (Throwable e) {
      LOG.error("Got exception:", e);
      responder.sendStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
  }
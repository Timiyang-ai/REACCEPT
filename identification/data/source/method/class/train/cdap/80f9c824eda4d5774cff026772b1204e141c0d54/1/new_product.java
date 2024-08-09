private void getScheduledRuntime(HttpResponder responder, String namespaceId, String appId, String workflowId,
                                   String prevOrNext) {
    try {
      Id.Program id = Id.Program.from(namespaceId, appId, ProgramType.WORKFLOW, workflowId);
      List<ScheduledRuntime> runtimes;
      if (prevOrNext.equals("previous")) {
        runtimes = scheduler.previousScheduledRuntime(id, SchedulableProgramType.WORKFLOW);
      } else {
        runtimes = scheduler.nextScheduledRuntime(id, SchedulableProgramType.WORKFLOW);
      }
      responder.sendJson(HttpResponseStatus.OK, runtimes);
    } catch (SecurityException e) {
      responder.sendStatus(HttpResponseStatus.UNAUTHORIZED);
    } catch (Throwable e) {
      LOG.error("Got exception:", e);
      responder.sendStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
  }
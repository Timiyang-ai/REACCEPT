protected String getProgramSpecification(Id.Program id, ProgramType type)
    throws Exception {

    ApplicationSpecification appSpec;
    try {
      appSpec = store.getApplication(id.getApplication());
      if (appSpec == null) {
        return "";
      }
      String runnableId = id.getId();
      if (type == ProgramType.FLOW && appSpec.getFlows().containsKey(runnableId)) {
        return GSON.toJson(appSpec.getFlows().get(id.getId()));
      } else if (type == ProgramType.PROCEDURE && appSpec.getProcedures().containsKey(runnableId)) {
        return GSON.toJson(appSpec.getProcedures().get(id.getId()));
      } else if (type == ProgramType.MAPREDUCE && appSpec.getMapReduce().containsKey(runnableId)) {
        return GSON.toJson(appSpec.getMapReduce().get(id.getId()));
      } else if (type == ProgramType.SPARK && appSpec.getSpark().containsKey(runnableId)) {
        return GSON.toJson(appSpec.getSpark().get(id.getId()));
      } else if (type == ProgramType.WORKFLOW && appSpec.getWorkflows().containsKey(runnableId)) {
        return GSON.toJson(appSpec.getWorkflows().get(id.getId()));
      } else if (type == ProgramType.SERVICE && appSpec.getServices().containsKey(runnableId)) {
        return GSON.toJson(appSpec.getServices().get(id.getId()));
      }
    } catch (Throwable throwable) {
      LOG.warn(throwable.getMessage(), throwable);
      throw new Exception(throwable.getMessage());
    }
    return "";
  }
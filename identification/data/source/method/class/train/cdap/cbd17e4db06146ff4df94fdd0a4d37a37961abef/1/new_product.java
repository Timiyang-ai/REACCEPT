@Nullable
  private ProgramSpecification getProgramSpecification(Id.Program id, ProgramType type) throws Exception {
    ApplicationSpecification appSpec;
    try {
      appSpec = store.getApplication(id.getApplication());
      if (appSpec == null) {
        return null;
      }

      String runnableId = id.getId();
      ProgramSpecification programSpec;
      if (type == ProgramType.FLOW && appSpec.getFlows().containsKey(runnableId)) {
        programSpec = appSpec.getFlows().get(id.getId());
      } else if (type == ProgramType.PROCEDURE && appSpec.getProcedures().containsKey(runnableId)) {
        programSpec = appSpec.getProcedures().get(id.getId());
      } else if (type == ProgramType.MAPREDUCE && appSpec.getMapReduce().containsKey(runnableId)) {
        programSpec = appSpec.getMapReduce().get(id.getId());
      } else if (type == ProgramType.SPARK && appSpec.getSpark().containsKey(runnableId)) {
        programSpec = appSpec.getSpark().get(id.getId());
      } else if (type == ProgramType.WORKFLOW && appSpec.getWorkflows().containsKey(runnableId)) {
        programSpec = appSpec.getWorkflows().get(id.getId());
      } else if (type == ProgramType.SERVICE && appSpec.getServices().containsKey(runnableId)) {
        programSpec = appSpec.getServices().get(id.getId());
      } else {
        programSpec = null;
      }
      return programSpec;
    } catch (Throwable throwable) {
      LOG.warn(throwable.getMessage(), throwable);
      throw new Exception(throwable.getMessage());
    }
  }
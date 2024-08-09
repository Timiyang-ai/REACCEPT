synchronized AssignedTask assignTask(
      String taskId,
      String slaveHost,
      SlaveID slaveId,
      Set<Integer> assignedPorts) {

    checkNotBlank(taskId);
    checkNotBlank(slaveHost);
    checkNotNull(assignedPorts);

    TaskAssignMutation mutation = assignHost(slaveHost, slaveId, assignedPorts);
    changeState(Query.byId(taskId), mutation);

    return mutation.getAssignedTask();
  }
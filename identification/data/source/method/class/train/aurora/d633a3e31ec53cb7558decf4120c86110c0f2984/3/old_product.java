synchronized AssignedTask assignTask(String taskId, String slaveHost, SlaveID slaveId,
      Set<Integer> assignedPorts) {
    checkNotBlank(taskId);
    checkNotBlank(slaveHost);
    checkNotNull(assignedPorts);

    final AtomicReference<AssignedTask> returnValue = new AtomicReference<AssignedTask>();
    changeState(Query.byId(taskId), assignHost(slaveHost, slaveId, returnValue, assignedPorts));

    return returnValue.get();
  }
public List<JobCommand> workerHeartbeat(long workerId, List<TaskInfo> taskInfoList) {
    // Run under shared lock for mWorkers
    try (LockResource workersLockShared = new LockResource(mWorkerRWLock.readLock())) {
      MasterWorkerInfo worker = mWorkers.getFirstByField(mIdIndex, workerId);
      if (worker == null) {
        return Collections.singletonList(JobCommand.registerCommand(new RegisterCommand()));
      }
      // Update last-update-time of this particular worker under lock
      // to prevent lost worker detector clearing it under race
      worker.updateLastUpdatedTimeMs();
    }

    // Update task infos for all jobs involved
    Map<Long, List<TaskInfo>> taskInfosPerJob = new HashMap<>();
    for (TaskInfo taskInfo : taskInfoList) {
      if (!taskInfosPerJob.containsKey(taskInfo.getJobId())) {
        taskInfosPerJob.put(taskInfo.getJobId(), new ArrayList<TaskInfo>());
      }
      taskInfosPerJob.get(taskInfo.getJobId()).add(taskInfo);
    }
    for (Map.Entry<Long, List<TaskInfo>> taskInfosPair : taskInfosPerJob.entrySet()) {
      JobCoordinator jobCoordinator = mIdToJobCoordinator.get(taskInfosPair.getKey());
      jobCoordinator.updateTasks(taskInfosPair.getValue());
    }
    return mCommandManager.pollAllPendingCommands(workerId);
  }
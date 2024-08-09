private void recoverWorkers(final List<MesosWorkerStore.Worker> tasksFromPreviousAttempts) {
		assert(workersInNew.isEmpty());
		assert(workersInLaunch.isEmpty());
		assert(workersBeingReturned.isEmpty());

		if (!tasksFromPreviousAttempts.isEmpty()) {
			LOG.info("Retrieved {} TaskManagers from previous attempt", tasksFromPreviousAttempts.size());

			List<Tuple2<TaskRequest, String>> toAssign = new ArrayList<>(tasksFromPreviousAttempts.size());

			for (final MesosWorkerStore.Worker worker : tasksFromPreviousAttempts) {
				switch(worker.state()) {
					case Launched:
						workersInLaunch.put(extractResourceID(worker.taskID()), worker);
						final LaunchableMesosWorker launchable = createLaunchableMesosWorker(worker.taskID());
						toAssign.add(new Tuple2<>(launchable.taskRequest(), worker.hostname().get()));
						break;
					case Released:
						workersBeingReturned.put(extractResourceID(worker.taskID()), worker);
						break;
				}
				taskMonitor.tell(new TaskMonitor.TaskGoalStateUpdated(extractGoalState(worker)), selfActor);
			}

			// tell the launch coordinator about prior assignments
			if (toAssign.size() >= 1) {
				launchCoordinator.tell(new LaunchCoordinator.Assign(toAssign), selfActor);
			}
		}
	}
private void recoverWorkers() throws Exception {
		// if this resource manager is recovering from failure,
		// then some worker tasks are most likely still alive and we can re-obtain them
		final List<MesosWorkerStore.Worker> tasksFromPreviousAttempts = workerStore.recoverWorkers();

		assert(workersInNew.isEmpty());
		assert(workersInLaunch.isEmpty());
		assert(workersBeingReturned.isEmpty());

		if (!tasksFromPreviousAttempts.isEmpty()) {
			LOG.info("Retrieved {} TaskManagers from previous attempt", tasksFromPreviousAttempts.size());

			List<Tuple2<TaskRequest, String>> toAssign = new ArrayList<>(tasksFromPreviousAttempts.size());

			for (final MesosWorkerStore.Worker worker : tasksFromPreviousAttempts) {
				LaunchableMesosWorker launchable = createLaunchableMesosWorker(worker.taskID(), worker.profile());

				switch(worker.state()) {
					case New:
						// remove new workers because allocation requests are transient
						workerStore.removeWorker(worker.taskID());
						break;
					case Launched:
						workersInLaunch.put(extractResourceID(worker.taskID()), worker);
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
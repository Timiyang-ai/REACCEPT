@Override
	protected void requestNewWorkers(int numWorkers) {

		try {
			List<TaskMonitor.TaskGoalStateUpdated> toMonitor = new ArrayList<>(numWorkers);
			List<LaunchableTask> toLaunch = new ArrayList<>(numWorkers);

			// generate new workers into persistent state and launch associated actors
			for (int i = 0; i < numWorkers; i++) {
				MesosWorkerStore.Worker worker = MesosWorkerStore.Worker.newWorker(workerStore.newTaskID());
				workerStore.putWorker(worker);
				workersInNew.put(extractResourceID(worker.taskID()), worker);

				LaunchableMesosWorker launchable = createLaunchableMesosWorker(worker.taskID());

				LOG.info("Scheduling Mesos task {} with ({} MB, {} cpus).",
					launchable.taskID().getValue(), launchable.taskRequest().getMemory(), launchable.taskRequest().getCPUs());

				toMonitor.add(new TaskMonitor.TaskGoalStateUpdated(extractGoalState(worker)));
				toLaunch.add(launchable);
			}

			// tell the task router about the new plans
			for (TaskMonitor.TaskGoalStateUpdated update : toMonitor) {
				taskRouter.tell(update, self());
			}

			// tell the launch coordinator to launch the new tasks
			if(toLaunch.size() >= 1) {
				launchCoordinator.tell(new LaunchCoordinator.Launch(toLaunch), self());
			}
		}
		catch(Exception ex) {
			fatalError("unable to request new workers", ex);
		}
	}
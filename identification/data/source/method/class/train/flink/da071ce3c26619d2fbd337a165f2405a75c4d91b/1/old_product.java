@RpcMethod
	public void acceptOffers(AcceptOffers msg) {
		try {
			List<TaskMonitor.TaskGoalStateUpdated> toMonitor = new ArrayList<>(msg.operations().size());

			// transition the persistent state of some tasks to Launched
			for (Protos.Offer.Operation op : msg.operations()) {
				if (op.getType() == Protos.Offer.Operation.Type.LAUNCH) {
					for (Protos.TaskInfo info : op.getLaunch().getTaskInfosList()) {
						MesosWorkerStore.Worker worker = workersInNew.remove(extractResourceID(info.getTaskId()));
						assert (worker != null);

						worker = worker.launchWorker(info.getSlaveId(), msg.hostname());
						workerStore.putWorker(worker);
						workersInLaunch.put(extractResourceID(worker.taskID()), worker);

						LOG.info("Launching Mesos task {} on host {}.",
							worker.taskID().getValue(), worker.hostname().get());

						toMonitor.add(new TaskMonitor.TaskGoalStateUpdated(extractGoalState(worker)));
					}
				}
			}

			// tell the task monitor about the new plans
			for (TaskMonitor.TaskGoalStateUpdated update : toMonitor) {
				taskMonitor.tell(update, selfActor);
			}

			// send the acceptance message to Mesos
			schedulerDriver.acceptOffers(msg.offerIds(), msg.operations(), msg.filters());
		}
		catch(Exception ex) {
			onFatalError(new ResourceManagerException("unable to accept offers", ex));
		}
	}
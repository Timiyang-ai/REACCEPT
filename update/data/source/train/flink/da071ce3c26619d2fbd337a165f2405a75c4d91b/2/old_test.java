@Test
	public void testAcceptOffers() throws Exception {
		new Context() {{
			startResourceManager();

			// allocate a new worker
			MesosWorkerStore.Worker worker1 = allocateWorker(task1, resourceProfile1);

			// send an AcceptOffers message as the LaunchCoordinator would
			// to launch task1 onto slave1 with offer1
			Protos.TaskInfo task1info = Protos.TaskInfo.newBuilder()
				.setTaskId(task1).setName("").setSlaveId(slave1).build();
			AcceptOffers msg = new AcceptOffers(slave1host, singletonList(offer1), singletonList(launch(task1info)));
			resourceManager.acceptOffers(msg);

			// verify that the worker was persisted, the internal state was updated,
			// Mesos was asked to launch task1, and the task router was notified
			MesosWorkerStore.Worker worker1launched = worker1.launchWorker(slave1, slave1host);
			verify(rmServices.workerStore).putWorker(worker1launched);
			assertThat(resourceManager.workersInNew.entrySet(), empty());
			assertThat(resourceManager.workersInLaunch, hasEntry(extractResourceID(task1), worker1launched));
			resourceManager.taskRouter.expectMsg(
				new TaskMonitor.TaskGoalStateUpdated(extractGoalState(worker1launched)));
			verify(rmServices.schedulerDriver).acceptOffers(msg.offerIds(), msg.operations(), msg.filters());
		}};
	}
@Test
	public void testStopWorker() throws Exception {
		new Context() {{
			// set the initial persistent state with a launched worker
			MesosWorkerStore.Worker worker1launched = MesosWorkerStore.Worker.newWorker(task1).launchWorker(slave1, slave1host);
			when(rmServices.workerStore.getFrameworkID()).thenReturn(Option.apply(framework1));
			when(rmServices.workerStore.recoverWorkers()).thenReturn(singletonList(worker1launched));
			startResourceManager();

			// drain the assign message
			resourceManager.launchCoordinator.expectMsgClass(LaunchCoordinator.Assign.class);

			// tell the RM to stop the worker
			resourceManager.stopWorker(new RegisteredMesosWorkerNode(worker1launched));

			// verify that the instance state was updated
			MesosWorkerStore.Worker worker1Released = worker1launched.releaseWorker();
			verify(rmServices.workerStore).putWorker(worker1Released);
			assertThat(resourceManager.workersInLaunch.entrySet(), empty());
			assertThat(resourceManager.workersBeingReturned, hasEntry(extractResourceID(task1), worker1Released));

			// verify that the monitor was notified
			resourceManager.taskRouter.expectMsgClass(TaskMonitor.TaskGoalStateUpdated.class);
			resourceManager.launchCoordinator.expectMsgClass(LaunchCoordinator.Unassign.class);
		}};
	}
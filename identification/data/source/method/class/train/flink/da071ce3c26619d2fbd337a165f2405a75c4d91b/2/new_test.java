@Test
	public void testRecoverWorkers() throws Exception {
		new Context() {{
			// set the initial persistent state then initialize the RM
			MesosWorkerStore.Worker worker1 = MesosWorkerStore.Worker.newWorker(task1);
			MesosWorkerStore.Worker worker2 = MesosWorkerStore.Worker.newWorker(task2).launchWorker(slave1, slave1host);
			MesosWorkerStore.Worker worker3 = MesosWorkerStore.Worker.newWorker(task3).launchWorker(slave1, slave1host).releaseWorker();
			when(rmServices.workerStore.getFrameworkID()).thenReturn(Option.apply(framework1));
			when(rmServices.workerStore.recoverWorkers()).thenReturn(Arrays.asList(worker1, worker2, worker3));
			startResourceManager();

			// verify that the internal state was updated, the task router was notified,
			// and the launch coordinator was asked to launch a task.
			// note: "new" workers are discarded
			assertThat(resourceManager.workersInNew.entrySet(), empty());
			assertThat(resourceManager.workersInLaunch, hasEntry(extractResourceID(task2), worker2));
			assertThat(resourceManager.workersBeingReturned, hasEntry(extractResourceID(task3), worker3));
			resourceManager.taskRouter.expectMsgClass(TaskMonitor.TaskGoalStateUpdated.class);
			LaunchCoordinator.Assign actualAssign =
				resourceManager.launchCoordinator.expectMsgClass(LaunchCoordinator.Assign.class);
			assertThat(actualAssign.tasks(), hasSize(1));
			assertThat(actualAssign.tasks().get(0).f0.getId(), equalTo(task2.getValue()));
			assertThat(actualAssign.tasks().get(0).f1, equalTo(slave1host));
			resourceManager.launchCoordinator.expectNoMsg();
		}};
	}
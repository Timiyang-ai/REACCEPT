@Test
	public void testRequestNewWorkers() {
		new Context() {{
			new Within(duration("10 seconds")) {
				@Override
				protected void run() {
					try {
						initialize();
						register(Collections.<ResourceID>emptyList());

						// set the target pool size
						when(workerStore.newTaskID()).thenReturn(task1).thenThrow(new AssertionFailedError());
						resourceManager.tell(new SetWorkerPoolSize(1), jobManager);

						// verify that a new worker was persisted, the internal state was updated, the task router was notified,
						// and the launch coordinator was asked to launch a task
						MesosWorkerStore.Worker expected = MesosWorkerStore.Worker.newWorker(task1);
						verify(workerStore).putWorker(expected);
						assertThat(resourceManagerInstance.workersInNew, hasEntry(extractResourceID(task1), expected));
						resourceManagerInstance.taskRouter.expectMsgClass(TaskMonitor.TaskGoalStateUpdated.class);
						resourceManagerInstance.launchCoordinator.expectMsgClass(LaunchCoordinator.Launch.class);
					}
					catch(Exception ex) {
						throw new RuntimeException(ex);
					}
				}
			};
		}};
	}
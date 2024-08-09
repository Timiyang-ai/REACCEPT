@Test
	public void testAcceptOffers() {
		new Context() {{
			new Within(duration("10 seconds")) {
				@Override
				protected void run() {
					try {
						// set the initial persistent state with a new task then initialize the RM
						MesosWorkerStore.Worker worker1 = MesosWorkerStore.Worker.newWorker(task1);
						when(workerStore.getFrameworkID()).thenReturn(Option.apply(framework1));
						when(workerStore.recoverWorkers()).thenReturn(singletonList(worker1));
						initialize();
						assertThat(resourceManagerInstance.workersInNew, hasEntry(extractResourceID(task1), worker1));
						resourceManagerInstance.taskRouter.expectMsgClass(TaskMonitor.TaskGoalStateUpdated.class);
						register(Collections.<ResourceID>emptyList());

						// send an AcceptOffers message as the LaunchCoordinator would
						// to launch task1 onto slave1 with offer1
						Protos.TaskInfo task1info = Protos.TaskInfo.newBuilder()
							.setTaskId(task1).setName("").setSlaveId(slave1).build();
						AcceptOffers msg = new AcceptOffers(slave1host, singletonList(offer1), singletonList(launch(task1info)));
						resourceManager.tell(msg);

						// verify that the worker was persisted, the internal state was updated,
						// Mesos was asked to launch task1, and the task router was notified
						MesosWorkerStore.Worker worker1launched = worker1.launchWorker(slave1, slave1host);
						verify(workerStore).putWorker(worker1launched);
						assertThat(resourceManagerInstance.workersInNew.entrySet(), empty());
						assertThat(resourceManagerInstance.workersInLaunch, hasEntry(extractResourceID(task1), worker1launched));
						resourceManagerInstance.taskRouter.expectMsg(
							new TaskMonitor.TaskGoalStateUpdated(extractGoalState(worker1launched)));
						verify(schedulerDriver).acceptOffers(msg.offerIds(), msg.operations(), msg.filters());
					}
					catch(Exception ex) {
						throw new RuntimeException(ex);
					}
				}
			};
		}};
	}
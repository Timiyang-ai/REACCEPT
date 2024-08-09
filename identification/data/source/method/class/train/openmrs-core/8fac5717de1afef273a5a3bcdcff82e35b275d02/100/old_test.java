	@Test
	public void saveTask_shouldSaveTaskToTheDatabase() throws Exception {
		log.debug("saveTask_shouldSaveTaskToTheDatabase start");
		SchedulerService service = Context.getSchedulerService();
		
		TaskDefinition def = new TaskDefinition();
		final String TASK_NAME = "This is my test! 123459876";
		def.setName(TASK_NAME);
		def.setStartOnStartup(false);
		def.setRepeatInterval(10000000L);
		def.setTaskClass(LatchExecuteTask.class.getName());
		
		synchronized (TASK_TEST_METHOD_LOCK) {
			Collection<TaskDefinition> tasks = service.getRegisteredTasks();
			for (TaskDefinition task : tasks) {
				log.debug("Task dump 1: " + task);
			}
			int size = tasks.size();
			service.saveTaskDefinition(def);
			tasks = service.getRegisteredTasks();
			for (TaskDefinition task : tasks) {
				log.debug("Task dump 2:" + task);
			}
			Assert.assertEquals(size + 1, tasks.size());
		}
		
		def = service.getTaskByName(TASK_NAME);
		Assert.assertEquals(Context.getAuthenticatedUser().getUserId(), def.getCreator().getUserId());
		log.debug("saveTask_shouldSaveTaskToTheDatabase end");
	}
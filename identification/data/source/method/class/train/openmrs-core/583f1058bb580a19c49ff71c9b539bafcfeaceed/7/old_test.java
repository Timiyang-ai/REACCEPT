	@Test
	public void scheduleTask_shouldHandleZeroRepeatInterval() throws SchedulerException {
		
		// Represents the start time of the task (right now)
		Calendar startTime = Calendar.getInstance();
		
		// Define repeatInterval as zero
		Long repeatInterval = 0L;
		
		String taskName = "TestTask";
		String className = "org.openmrs.scheduler.tasks.TestTask";
		
		Boolean startOnStartup = false;
		
		// Create the new task
		TaskDefinition taskDefinition = new TaskDefinition();
		taskDefinition.setName(taskName);
		taskDefinition.setTaskClass(className);
		taskDefinition.setStartTime(startTime.getTime());
		taskDefinition.setRepeatInterval(repeatInterval);
		taskDefinition.setStartOnStartup(startOnStartup);
		
		Task clientTask = null;
		
		clientTask = Context.getSchedulerService().scheduleTask(taskDefinition);
		
		// without this commit there seems to be a table lock left on the SCHEDULER_TASK_CONFIG table, see TRUNK-4212
		Context.flushSession();
		
		// Assert that the clientTask is not null, i.e. the sheduleTask was able to successfully schedule in case of zero repeatInterval.
		assertNotNull(
		    "The clientTask variable is null, so either the TimerSchedulerServiceImpl.scheduleTask method hasn't finished or didn't get run",
		    clientTask);
	}
	@Test
	public void getNextExecution_shouldGetTheCorrectRepeatInterval() {
		
		// Represents the start time of the task (right now)
		Calendar startTime = Calendar.getInstance();
		
		// Execute task every 4 minutes
		Long repeatInterval = (long) (4 * 60);
		
		// Create the new task
		TaskDefinition taskDefinition = new TaskDefinition();
		taskDefinition.setStartTime(startTime.getTime());
		taskDefinition.setRepeatInterval(repeatInterval);
		
		// Add four minutes to the start time
		startTime.add(Calendar.MINUTE, 4);
		
		// Get the next scheduled execution time for this task 
		Date nextTime = SchedulerUtil.getNextExecution(taskDefinition);
		
		// Assert that the next execution time is equal to startTime + 4 minutes
		assertEquals(startTime.getTime(), nextTime);
		
	}
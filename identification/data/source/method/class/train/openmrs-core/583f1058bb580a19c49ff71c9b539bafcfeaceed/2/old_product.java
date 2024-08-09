public Task scheduleTask(TaskDefinition taskDefinition) throws SchedulerException {
		Task clientTask = null;
		if (taskDefinition != null) {
			
			// Cancel any existing timer tasks for the same task definition
			// TODO Make sure this is the desired behavior 
			// TODO Do we ever want the same task definition to run more than once?
			TimerSchedulerTask schedulerTask = scheduledTasks.get(taskDefinition.getId());
			if (schedulerTask != null) {
				//schedulerTask.cancel();					
				log.info("Shutting down the existing instance of this task to avoid conflicts!!");
				schedulerTask.shutdown();
			}
			
			try {
				
				// Create new task from task definition 
				clientTask = TaskFactory.getInstance().createInstance(taskDefinition);
				
				// if we were unable to get a class, just quit
				if (clientTask != null) {
					
					schedulerTask = new TimerSchedulerTask(clientTask);
					
					taskDefinition.setTaskInstance(clientTask);
					
					// Once this method is called, the timer is set to start at the given start time.
					// NOTE:  We need to adjust the repeat interval as the JDK Timer expects time in milliseconds and 
					// we record by seconds.  
					
					long repeatInterval = taskDefinition.getRepeatInterval()
					        * SchedulerConstants.SCHEDULER_MILLIS_PER_SECOND;
					
					if (taskDefinition.getStartTime() != null) {
						// Need to calculate the "next execution time" because the scheduled time is most likely in the past
						// and the JDK timer will run the task X number of times from the start time until now to catch up.
						Date nextTime = SchedulerUtil.getNextExecution(taskDefinition);
						
						// Start task at fixed rate at given future date and repeat as directed 							
						log.info("Starting task ... the task will execute for the first time at " + nextTime);
						
						// Schedule the task to run at a fixed rate
						timerScheduler.scheduleAtFixedRate(schedulerTask, nextTime, repeatInterval);
					} else if (repeatInterval > 0) {
						// Start task on repeating schedule, delay for SCHEDULER_DEFAULT_DELAY seconds	
						log.info("Delaying start time by " + SchedulerConstants.SCHEDULER_DEFAULT_DELAY + " seconds");
						timerScheduler.scheduleAtFixedRate(schedulerTask, SchedulerConstants.SCHEDULER_DEFAULT_DELAY,
						    repeatInterval);
					} else {
						// schedule for single execution, starting now
						log.info("Starting one-shot task");
						timerScheduler.schedule(schedulerTask, new Date());
					}
					
					// Update task that has been started
					log.debug("Registering timer for task " + taskDefinition.getId());
					
					//  Add the new timer to the scheduler running task list  
					scheduledTasks.put(taskDefinition.getId(), schedulerTask);
					
					// Update the timer status in the database
					taskDefinition.setStarted(true);
					saveTask(taskDefinition);
				}
			}
			catch (Exception e) {
				log.error("Failed to schedule task " + taskDefinition.getName(), e);
				throw new SchedulerException(e);
			}
		}
		return clientTask;
	}
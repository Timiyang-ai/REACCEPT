public static Date getNextExecution(TaskDefinition taskDefinition) {
		Calendar nextTime = Calendar.getInstance();
		
		try {
			Date firstTime = taskDefinition.getStartTime();
			
			if (firstTime != null) {
				
				// Right now
				Date currentTime = new Date();
				
				// If the first time is actually in the future, then we use that date/time
				if (firstTime.after(currentTime)) {
					return firstTime;
				}
				
				// The time between successive runs (e.g. 24 hours)
				long repeatInterval = taskDefinition.getRepeatInterval().longValue();
				if (repeatInterval == 0) {
					// task is one-shot so just return the start time
					return firstTime;
				}
				
				// Calculate time between the first time the process was run and right now (e.g. 3 days, 15 hours)
				long betweenTime = currentTime.getTime() - firstTime.getTime();
				
				// Calculate the last time the task was run   (e.g. 15 hours ago)
				long lastTime = (betweenTime % (repeatInterval * 1000));
				
				// Calculate the time to add to the current time (e.g. 24 hours - 15 hours = 9 hours)
				long additional = ((repeatInterval * 1000) - lastTime);
				
				nextTime.setTime(new Date(currentTime.getTime() + additional));
				
				log.debug("The task " + taskDefinition.getName() + " will start at " + nextTime.getTime());
			}
		}
		catch (Exception e) {
			log.error("Failed to get next execution time for " + taskDefinition.getName(), e);
		}
		
		return nextTime.getTime();
	}
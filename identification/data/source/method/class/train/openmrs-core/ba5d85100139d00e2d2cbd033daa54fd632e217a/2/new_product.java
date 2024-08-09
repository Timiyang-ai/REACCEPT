@Override
	public void validate(Object obj, Errors errors) {
		TaskDefinition taskDefinition = (TaskDefinition) obj;
		
		if (taskDefinition == null) {
			errors.rejectValue("task", "error.general");
		} else {
			//Won't work without name and description properties on Task Definition
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Scheduler.taskForm.required", new Object[] {
			        "Task name", taskDefinition.getName() });
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskClass", "Scheduler.taskForm.required", new Object[] {
			        "Task class", taskDefinition.getTaskClass() });
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatInterval", "Scheduler.taskForm.required", new Object[] {
			        "Repeat interval", taskDefinition.getRepeatInterval() });
			
			ValidateUtil
			        .validateFieldLengths(errors, obj.getClass(), "name", "description", "taskClass", "startTimePattern");
			
			// Check if the class is valid
			try {
				Class<?> taskClass = OpenmrsClassLoader.getInstance().loadClass(taskDefinition.getTaskClass());
				
				Object o = taskClass.newInstance();
				if (!(o instanceof Task)) {
					errors
					        .rejectValue("taskClass", "Scheduler.taskForm.classDoesNotImplementTask", new Object[] {
					                taskDefinition.getTaskClass(), Task.class.getName() },
					            "Class does not implement Task interface");
				}
				
			}
			catch (IllegalAccessException iae) {
				errors.rejectValue("taskClass", "Scheduler.taskForm.illegalAccessException", new Object[] { taskDefinition
				        .getTaskClass() }, "Illegal access exception.");
			}
			catch (InstantiationException ie) {
				errors.rejectValue("taskClass", "Scheduler.taskForm.instantiationException", new Object[] { taskDefinition
				        .getTaskClass() }, "Error creating new instance of class.");
			}
			catch (ClassNotFoundException cnfe) {
				errors.rejectValue("taskClass", "Scheduler.taskForm.classNotFoundException", new Object[] { taskDefinition
				        .getTaskClass() }, "Class not found error.");
			}
		}
	}
@Override
	public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {

		logger.debug("TaskMapperContext {} in KafkaPublishTaskMapper", taskMapperContext);

		WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
		Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
		String taskId = taskMapperContext.getTaskId();
		int retryCount = taskMapperContext.getRetryCount();

		TaskDef taskDefinition = Optional.ofNullable(taskMapperContext.getTaskDefinition())
				.orElseGet(() -> Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
						.orElseThrow(() -> {
							String reason = String.format("Invalid task specified. Cannot find task by name %s in the task definitions", taskToSchedule.getName());
							return new TerminateWorkflowException(reason);
						}));

		Map<String, Object> input = parametersUtils.getTaskInputV2(taskToSchedule.getInputParameters(), workflowInstance, taskId, taskDefinition);

		Task kafkaPublishTask = new Task();
		kafkaPublishTask.setTaskType(taskToSchedule.getType());
		kafkaPublishTask.setTaskDefName(taskToSchedule.getName());
		kafkaPublishTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
		kafkaPublishTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
		kafkaPublishTask.setWorkflowType(workflowInstance.getWorkflowName());
		kafkaPublishTask.setCorrelationId(workflowInstance.getCorrelationId());
		kafkaPublishTask.setScheduledTime(System.currentTimeMillis());
		kafkaPublishTask.setTaskId(taskId);
		kafkaPublishTask.setInputData(input);
		kafkaPublishTask.setStatus(Task.Status.SCHEDULED);
		kafkaPublishTask.setRetryCount(retryCount);
		kafkaPublishTask.setCallbackAfterSeconds(taskToSchedule.getStartDelay());
		kafkaPublishTask.setWorkflowTask(taskToSchedule);
		kafkaPublishTask.setRateLimitPerFrequency(taskDefinition.getRateLimitPerFrequency());
		kafkaPublishTask.setRateLimitFrequencyInSeconds(taskDefinition.getRateLimitFrequencyInSeconds());
		return Collections.singletonList(kafkaPublishTask);
	}
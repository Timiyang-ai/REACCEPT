@Override
	public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {

		logger.debug("TaskMapperContext {} in KafkaPublishTaskMapper", taskMapperContext);

		WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
		Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
		String taskId = taskMapperContext.getTaskId();
		int retryCount = taskMapperContext.getRetryCount();

		TaskDef taskDefinition = Optional.ofNullable(taskMapperContext.getTaskDefinition())
						.orElseGet(() -> Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
						.orElse(null));

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
		kafkaPublishTask.setWorkflowPriority(workflowInstance.getPriority());
		if (Objects.nonNull(taskDefinition)) {
			kafkaPublishTask.setExecutionNameSpace(taskDefinition.getExecutionNameSpace());
			kafkaPublishTask.setIsolationGroupId(taskDefinition.getIsolationGroupId());
			kafkaPublishTask.setRateLimitPerFrequency(taskDefinition.getRateLimitPerFrequency());
			kafkaPublishTask.setRateLimitFrequencyInSeconds(taskDefinition.getRateLimitFrequencyInSeconds());
		}
		return Collections.singletonList(kafkaPublishTask);
	}
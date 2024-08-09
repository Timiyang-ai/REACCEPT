	@Test
	public void getMappedTasks() {
		//Given
		WorkflowTask taskToSchedule = new WorkflowTask();
		taskToSchedule.setName("kafka_task");
		taskToSchedule.setType(TaskType.KAFKA_PUBLISH.name());
		taskToSchedule.setTaskDefinition(new TaskDef("kafka_task"));
		String taskId = IDGenerator.generate();
		String retriedTaskId = IDGenerator.generate();

		Workflow workflow = new Workflow();
		WorkflowDef workflowDef = new WorkflowDef();
		workflow.setWorkflowDefinition(workflowDef);

		TaskMapperContext taskMapperContext = TaskMapperContext.newBuilder()
				.withWorkflowDefinition(workflowDef)
				.withWorkflowInstance(workflow)
				.withTaskDefinition(new TaskDef())
				.withTaskToSchedule(taskToSchedule)
				.withTaskInput(new HashMap<>())
				.withRetryCount(0)
				.withRetryTaskId(retriedTaskId)
				.withTaskId(taskId)
				.build();

		//when
		List<Task> mappedTasks = kafkaTaskMapper.getMappedTasks(taskMapperContext);

		//Then
		assertEquals(1, mappedTasks.size());
		assertEquals(TaskType.KAFKA_PUBLISH.name(), mappedTasks.get(0).getTaskType());
	}
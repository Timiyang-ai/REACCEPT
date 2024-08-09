    @Test
    public void getMappedTasks() {
        //Given
        WorkflowTask taskToSchedule = new WorkflowTask();
        taskToSchedule.setName("user_task");
        taskToSchedule.setType(TaskType.USER_DEFINED.name());
        taskToSchedule.setTaskDefinition(new TaskDef("user_task"));
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
        List<Task> mappedTasks = userDefinedTaskMapper.getMappedTasks(taskMapperContext);

        //Then
        assertEquals(1, mappedTasks.size());
        assertEquals(TaskType.USER_DEFINED.name(), mappedTasks.get(0).getTaskType());
    }
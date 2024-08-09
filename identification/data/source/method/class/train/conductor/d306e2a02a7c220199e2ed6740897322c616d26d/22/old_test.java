    @SuppressWarnings("unchecked")
    @Test
    public void getMappedTasks() {

        WorkflowTask workflowTask = new WorkflowTask();
        workflowTask.setName("DynoTask");
        workflowTask.setDynamicTaskNameParam("dynamicTaskName");
        TaskDef taskDef = new TaskDef();
        taskDef.setName("DynoTask");
        workflowTask.setTaskDefinition(taskDef);

        Map<String, Object> taskInput = new HashMap<>();
        taskInput.put("dynamicTaskName", "DynoTask");

        when(parametersUtils.getTaskInput(anyMap(), any(Workflow.class), any(TaskDef.class), anyString())).thenReturn(taskInput);

        String taskId = IDGenerator.generate();

        Workflow workflow = new Workflow();
        WorkflowDef workflowDef = new WorkflowDef();
        workflow.setWorkflowDefinition(workflowDef);

        TaskMapperContext taskMapperContext = TaskMapperContext.newBuilder()
                .withWorkflowInstance(workflow)
                .withWorkflowDefinition(workflowDef)
                .withTaskDefinition(workflowTask.getTaskDefinition())
                .withTaskToSchedule(workflowTask)
                .withTaskInput(taskInput)
                .withRetryCount(0)
                .withTaskId(taskId)
                .build();

        when(metadataDAO.getTaskDef("DynoTask")).thenReturn(new TaskDef());

        List<Task> mappedTasks = dynamicTaskMapper.getMappedTasks(taskMapperContext);

        assertEquals(1, mappedTasks.size());

        Task dynamicTask = mappedTasks.get(0);
        assertEquals(taskId, dynamicTask.getTaskId());
    }
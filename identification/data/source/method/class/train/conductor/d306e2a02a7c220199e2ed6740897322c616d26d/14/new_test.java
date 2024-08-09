    @Test
    public void getMappedTasks() throws Exception {

        WorkflowTask taskToSchedule = new WorkflowTask();
        taskToSchedule.setType(TaskType.JOIN.name());
        taskToSchedule.setJoinOn(Arrays.asList("task1", "task2"));

        String taskId = IDGenerator.generate();

        WorkflowDef  wd = new WorkflowDef();
        Workflow w = new Workflow();
        w.setWorkflowDefinition(wd);

        TaskMapperContext taskMapperContext = TaskMapperContext.newBuilder()
                .withWorkflowDefinition(wd)
                .withWorkflowInstance(w)
                .withTaskDefinition(new TaskDef())
                .withTaskToSchedule(taskToSchedule)
                .withRetryCount(0)
                .withTaskId(taskId)
                .build();

        List<Task> mappedTasks = new JoinTaskMapper().getMappedTasks(taskMapperContext);

        assertNotNull(mappedTasks);
        assertEquals(SystemTaskType.JOIN.name(), mappedTasks.get(0).getTaskType());
    }
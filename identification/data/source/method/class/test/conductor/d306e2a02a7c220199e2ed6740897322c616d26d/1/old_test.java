    @Test
    public void getMappedTasks() throws Exception {

        WorkflowTask  taskToSchedule = new WorkflowTask();
        taskToSchedule.setName("simple_task");
        taskToSchedule.setTaskDefinition(new TaskDef("simple_task"));

        String taskId = IDGenerator.generate();
        String retriedTaskId = IDGenerator.generate();

        WorkflowDef  wd = new WorkflowDef();
        Workflow w = new Workflow();
        w.setWorkflowDefinition(wd);

        TaskMapperContext taskMapperContext = TaskMapperContext.newBuilder()
                .withWorkflowDefinition(wd)
                .withWorkflowInstance(w)
                .withTaskDefinition(new TaskDef())
                .withTaskToSchedule(taskToSchedule)
                .withTaskInput(new HashMap<>())
                .withRetryCount(0)
                .withRetryTaskId(retriedTaskId)
                .withTaskId(taskId)
                .build();

        List<Task> mappedTasks = simpleTaskMapper.getMappedTasks(taskMapperContext);
        assertNotNull(mappedTasks);
        assertEquals(1, mappedTasks.size());
    }
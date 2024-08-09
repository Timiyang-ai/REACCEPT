@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {

        logger.debug("TaskMapperContext {} in DoWhileTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        String taskId = taskMapperContext.getTaskId();

        Map<String, Object> doWhileInput = new HashMap<>();
        doWhileInput.put("loopOver", taskToSchedule.getLoopOver());

        Task loopTask = new Task();
        loopTask.setTaskType(SystemTaskType.DO_WHILE.name());
        loopTask.setTaskDefName(SystemTaskType.DO_WHILE.name());
        loopTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        loopTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        loopTask.setCorrelationId(workflowInstance.getCorrelationId());
        loopTask.setWorkflowType(workflowInstance.getWorkflowName());
        loopTask.setScheduledTime(System.currentTimeMillis());
        loopTask.setInputData(doWhileInput);
        loopTask.setTaskId(taskId);
        loopTask.setStatus(Task.Status.IN_PROGRESS);
        loopTask.setWorkflowTask(taskToSchedule);

        return Arrays.asList(loopTask);
    }
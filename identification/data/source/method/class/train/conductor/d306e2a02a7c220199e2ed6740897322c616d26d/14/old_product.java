@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {
        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        String taskId = taskMapperContext.getTaskId();

        Map<String, Object> joinInput = new HashMap<>();
        joinInput.put("joinOn", taskToSchedule.getJoinOn());

        Task joinTask = new Task();
        joinTask.setTaskType(SystemTaskType.JOIN.name());
        joinTask.setTaskDefName(SystemTaskType.JOIN.name());
        joinTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        joinTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        joinTask.setCorrelationId(workflowInstance.getCorrelationId());
        joinTask.setScheduledTime(System.currentTimeMillis());
        joinTask.setEndTime(System.currentTimeMillis());
        joinTask.setInputData(joinInput);
        joinTask.setTaskId(taskId);
        joinTask.setStatus(Task.Status.IN_PROGRESS);
        joinTask.setWorkflowTask(taskToSchedule);

        return Arrays.asList(joinTask);
    }
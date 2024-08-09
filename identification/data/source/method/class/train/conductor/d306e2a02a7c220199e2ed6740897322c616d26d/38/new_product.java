@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {

        logger.debug("TaskMapperContext {} in UserDefinedTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        String taskId = taskMapperContext.getTaskId();
        int retryCount = taskMapperContext.getRetryCount();

        TaskDef taskDefinition = Optional.ofNullable(taskToSchedule.getTaskDefinition())
                .orElseThrow(() -> {
                    String reason = String.format("Invalid task specified. Cannot find task by name %s in the task definitions", taskToSchedule.getName());
                    return new TerminateWorkflowException(reason);
                });

        Map<String, Object> input = parametersUtils.getTaskInputV2(taskToSchedule.getInputParameters(), workflowInstance, taskId, taskDefinition);

        Task userDefinedTask = new Task();
        userDefinedTask.setTaskType(taskToSchedule.getType());
        userDefinedTask.setTaskDefName(taskToSchedule.getName());
        userDefinedTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        userDefinedTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        userDefinedTask.setWorkflowType(workflowInstance.getWorkflowName());
        userDefinedTask.setCorrelationId(workflowInstance.getCorrelationId());
        userDefinedTask.setScheduledTime(System.currentTimeMillis());
        userDefinedTask.setTaskId(taskId);
        userDefinedTask.setInputData(input);
        userDefinedTask.setStatus(Task.Status.SCHEDULED);
        userDefinedTask.setRetryCount(retryCount);
        userDefinedTask.setCallbackAfterSeconds(taskToSchedule.getStartDelay());
        userDefinedTask.setWorkflowTask(taskToSchedule);
        return Arrays.asList(userDefinedTask);
    }
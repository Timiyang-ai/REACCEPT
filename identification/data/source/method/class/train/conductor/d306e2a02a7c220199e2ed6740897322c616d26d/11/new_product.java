@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {
        logger.debug("TaskMapperContext {} in DynamicTaskMapper", taskMapperContext);
        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Map<String, Object> taskInput = taskMapperContext.getTaskInput();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        int retryCount = taskMapperContext.getRetryCount();
        String retriedTaskId = taskMapperContext.getRetryTaskId();

        String taskNameParam = taskToSchedule.getDynamicTaskNameParam();
        String taskName = getDynamicTaskName(taskInput, taskNameParam);
        taskToSchedule.setName(taskName);
        TaskDef taskDefinition = getDynamicTaskDefinition(taskToSchedule);

        Map<String, Object> input = parametersUtils.getTaskInput(taskToSchedule.getInputParameters(), workflowInstance,
                taskDefinition, taskMapperContext.getTaskId());
        Task dynamicTask = new Task();
        dynamicTask.setStartDelayInSeconds(taskToSchedule.getStartDelay());
        dynamicTask.setTaskId(taskMapperContext.getTaskId());
        dynamicTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        dynamicTask.setInputData(input);
        dynamicTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        dynamicTask.setWorkflowType(workflowInstance.getWorkflowType());
        dynamicTask.setStatus(Task.Status.SCHEDULED);
        dynamicTask.setTaskType(taskToSchedule.getType());
        dynamicTask.setTaskDefName(taskToSchedule.getName());
        dynamicTask.setCorrelationId(workflowInstance.getCorrelationId());
        dynamicTask.setScheduledTime(System.currentTimeMillis());
        dynamicTask.setRetryCount(retryCount);
        dynamicTask.setCallbackAfterSeconds(taskToSchedule.getStartDelay());
        dynamicTask.setResponseTimeoutSeconds(taskDefinition.getResponseTimeoutSeconds());
        dynamicTask.setWorkflowTask(taskToSchedule);
        dynamicTask.setTaskType(taskName);
        dynamicTask.setRetriedTaskId(retriedTaskId);
        return Arrays.asList(dynamicTask);
    }
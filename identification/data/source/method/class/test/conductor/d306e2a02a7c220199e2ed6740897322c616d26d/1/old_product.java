@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {

        logger.debug("TaskMapperContext {} in SimpleTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        int retryCount = taskMapperContext.getRetryCount();
        String retriedTaskId = taskMapperContext.getRetryTaskId();

        TaskDef taskDefinition = Optional.ofNullable(taskToSchedule.getTaskDefinition())
                .orElseThrow(() -> {
                    String reason = String.format("Invalid task. Task %s does not have a definition", taskToSchedule.getName());
                    return new TerminateWorkflowException(reason);
                });

        Map<String, Object> input = parametersUtils.getTaskInput(taskToSchedule.getInputParameters(), workflowInstance, taskDefinition, taskMapperContext.getTaskId());
        Task simpleTask = new Task();
        simpleTask.setStartDelayInSeconds(taskToSchedule.getStartDelay());
        simpleTask.setTaskId(taskMapperContext.getTaskId());
        simpleTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        simpleTask.setInputData(input);
        simpleTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        simpleTask.setWorkflowType(workflowInstance.getWorkflowName());
        simpleTask.setStatus(Task.Status.SCHEDULED);
        simpleTask.setTaskType(taskToSchedule.getName());
        simpleTask.setTaskDefName(taskToSchedule.getName());
        simpleTask.setCorrelationId(workflowInstance.getCorrelationId());
        simpleTask.setScheduledTime(System.currentTimeMillis());
        simpleTask.setRetryCount(retryCount);
        simpleTask.setCallbackAfterSeconds(taskToSchedule.getStartDelay());
        simpleTask.setResponseTimeoutSeconds(taskDefinition.getResponseTimeoutSeconds());
        simpleTask.setWorkflowTask(taskToSchedule);
        simpleTask.setRetriedTaskId(retriedTaskId);
        return Collections.singletonList(simpleTask);
    }
@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {

        logger.debug("TaskMapperContext {} in SimpleTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        int retryCount = taskMapperContext.getRetryCount();
        String retriedTaskId = taskMapperContext.getRetryTaskId();

        TaskDef taskDefinition = taskToSchedule.getTaskDefinition();
        if (taskDefinition == null) {
            taskDefinition = Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
                    .orElseThrow(() -> {
                        String reason = String.format("Invalid task specified. Cannot find task by name %s in the task definitions", taskToSchedule.getName());
                        return new TerminateWorkflowException(reason);
                    });
        }

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
        return Arrays.asList(simpleTask);
    }
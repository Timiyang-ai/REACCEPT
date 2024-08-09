@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        int retryCount = taskMapperContext.getRetryCount();
        String retriedTaskId = taskMapperContext.getRetryTaskId();

        TaskDef taskDefinition = Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
                .orElseThrow(() -> {
                    String reason = "Invalid task specified.  Cannot find task by name " + taskToSchedule.getName() + " in the task definitions";
                    return new TerminateWorkflow(reason);
                });

        String taskId = IDGenerator.generate();
        Map<String, Object> input = parametersUtils.getTaskInput(taskToSchedule.getInputParameters(), workflowInstance, taskDefinition, taskId);
        //return SystemTask.createSimpleTask(workflowInstance, taskId, taskToSchedule, input, taskDefinition, retryCount);
        Task theTask = new Task();
        theTask.setStartDelayInSeconds(taskToSchedule.getStartDelay());
        theTask.setTaskId(taskId);
        theTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        theTask.setInputData(input);
        theTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        theTask.setStatus(Task.Status.SCHEDULED);
        theTask.setTaskType(taskToSchedule.getName());
        theTask.setTaskDefName(taskToSchedule.getName());
        theTask.setCorrelationId(workflowInstance.getCorrelationId());
        theTask.setScheduledTime(System.currentTimeMillis());
        theTask.setRetryCount(retryCount);
        theTask.setCallbackAfterSeconds(taskToSchedule.getStartDelay());
        theTask.setResponseTimeoutSeconds(taskDefinition.getResponseTimeoutSeconds());
        theTask.setWorkflowTask(taskToSchedule);
        theTask.setRetriedTaskId(retriedTaskId);
        return Arrays.asList(theTask);
    }
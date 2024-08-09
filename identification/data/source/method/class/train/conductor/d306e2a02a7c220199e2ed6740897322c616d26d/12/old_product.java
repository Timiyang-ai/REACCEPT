@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {
        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Map<String, Object> taskInput = taskMapperContext.getTaskInput();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        int retryCount = taskMapperContext.getRetryCount();
        String retriedTaskId = taskMapperContext.getRetryTaskId();

        String paramName = taskToSchedule.getDynamicTaskNameParam();
        String taskName = (String) taskInput.get(paramName);
        if (taskName == null) {
            //Workflow should be terminated here...
            throw new TerminateWorkflow("Cannot map a dynamic task based on the parameter and input.  Parameter= " + paramName + ", input=" + taskInput);
        }

        taskToSchedule.setName(taskName);
        TaskDef taskDefinition = metadataDAO.getTaskDef(taskToSchedule.getName());

        if (taskDefinition == null) {
            String reason = "Invalid task specified.  Cannot find task by name " + taskToSchedule.getName() + " in the task definitions";
            throw new TerminateWorkflow(reason);
        }

        String taskId = IDGenerator.generate(); //QQ why not use the existing taskMapperContext.getTaskId()
        Map<String, Object> input = parametersUtils.getTaskInput(taskToSchedule.getInputParameters(), workflowInstance, taskDefinition, taskId);
        Task dynamicTask = new Task();
        dynamicTask.setStartDelayInSeconds(taskToSchedule.getStartDelay());
        dynamicTask.setTaskId(taskId);
        dynamicTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        dynamicTask.setInputData(input);
        dynamicTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        dynamicTask.setStatus(Task.Status.SCHEDULED);
        dynamicTask.setTaskType(taskToSchedule.getName());
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
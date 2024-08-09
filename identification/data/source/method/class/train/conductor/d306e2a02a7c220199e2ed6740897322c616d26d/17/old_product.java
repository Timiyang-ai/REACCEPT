@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {
        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        String taskId = taskMapperContext.getTaskId();
        int retryCount = taskMapperContext.getRetryCount();

        Map<String, Object> input;

        TaskDef taskDefinition = Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
                .orElseThrow(() -> {
                    String reason = "Invalid task specified.  Cannot find task by name " + taskToSchedule.getName() + " in the task definitions";
                    return new TerminateWorkflow(reason);
                });

        input = parametersUtils.getTaskInputV2(taskToSchedule.getInputParameters(), workflowInstance, taskId, taskDefinition);
        String taskType = taskToSchedule.getType();
        Task st = new Task();
        st.setTaskType(taskType);
        st.setTaskDefName(taskToSchedule.getName());
        st.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        st.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        st.setCorrelationId(workflowInstance.getCorrelationId());
        st.setScheduledTime(System.currentTimeMillis());
        st.setTaskId(taskId);
        st.setInputData(input);
        st.setStatus(Task.Status.SCHEDULED);
        st.setRetryCount(retryCount);
        st.setCallbackAfterSeconds(taskToSchedule.getStartDelay());
        st.setWorkflowTask(taskToSchedule);
        return Arrays.asList(st);
    }
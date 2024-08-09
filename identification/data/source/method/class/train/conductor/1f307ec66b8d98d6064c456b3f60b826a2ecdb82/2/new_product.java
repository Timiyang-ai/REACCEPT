@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {

        logger.debug("TaskMapperContext {} in DoWhileTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        String taskId = taskMapperContext.getTaskId();
        List<Task> tasksToBeScheduled = new ArrayList<>();
        int retryCount = taskMapperContext.getRetryCount();

        Task loopTask = new Task();
        loopTask.setTaskType(SystemTaskType.DO_WHILE.name());
        loopTask.setTaskDefName(SystemTaskType.DO_WHILE.name());
        loopTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        loopTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        loopTask.setCorrelationId(workflowInstance.getCorrelationId());
        loopTask.setWorkflowType(workflowInstance.getWorkflowName());
        loopTask.setScheduledTime(System.currentTimeMillis());
        loopTask.setTaskId(taskId);
        loopTask.setStatus(Task.Status.IN_PROGRESS);
        loopTask.setWorkflowTask(taskToSchedule);

        List<WorkflowTask>forkTasks = taskToSchedule.getLoopOver();
        for (WorkflowTask wft : forkTasks) {
            List<Task> tasks2 = taskMapperContext.getDeciderService()
                    .getTasksToBeScheduled(workflowInstance, wft, retryCount);
            tasksToBeScheduled.addAll(tasks2);
        }
        tasksToBeScheduled.add(loopTask);

        return tasksToBeScheduled;
    }
@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {

        logger.debug("TaskMapperContext {} in DoWhileTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();

        Task task = workflowInstance.getTaskByRefName(taskToSchedule.getTaskReferenceName());
        if (task != null && task.getStatus().isTerminal()) {
            //Since loopTask is already completed no need to schedule task again.
            return Collections.emptyList();
        }

        String taskId = taskMapperContext.getTaskId();
        List<Task> tasksToBeScheduled = new ArrayList<>();
        int retryCount = taskMapperContext.getRetryCount();
        TaskDef taskDefinition = Optional.ofNullable(taskMapperContext.getTaskDefinition())
                .orElseGet(() -> Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
                        .orElseThrow(() -> {
                            String reason = String.format("Invalid task specified. Cannot find task by name %s in the task definitions", taskToSchedule.getName());
                            return new TerminateWorkflowException(reason);
                        }));

        Task loopTask = new Task();
        loopTask.setTaskType(SystemTaskType.DO_WHILE.name());
        loopTask.setTaskDefName(taskToSchedule.getName());
        loopTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        loopTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        loopTask.setCorrelationId(workflowInstance.getCorrelationId());
        loopTask.setWorkflowType(workflowInstance.getWorkflowName());
        loopTask.setScheduledTime(System.currentTimeMillis());
        loopTask.setTaskId(taskId);
        loopTask.setIteration(1);
        loopTask.setStatus(Task.Status.SCHEDULED);
        loopTask.setWorkflowTask(taskToSchedule);
        loopTask.setRateLimitPerFrequency(taskDefinition.getRateLimitPerFrequency());
        loopTask.setRateLimitFrequencyInSeconds(taskDefinition.getRateLimitFrequencyInSeconds());

        tasksToBeScheduled.add(loopTask);
        List<WorkflowTask> loopOverTasks = taskToSchedule.getLoopOver();
        List<Task> tasks2 = taskMapperContext.getDeciderService()
                .getTasksToBeScheduled(workflowInstance, loopOverTasks.get(0), retryCount);
        tasksToBeScheduled.addAll(tasks2);

        return tasksToBeScheduled;
    }
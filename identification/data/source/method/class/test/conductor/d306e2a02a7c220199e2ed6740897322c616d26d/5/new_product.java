@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {

        logger.debug("TaskMapperContext {} in ForkJoinTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Map<String, Object> taskInput = taskMapperContext.getTaskInput();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        int retryCount = taskMapperContext.getRetryCount();

        String taskId = taskMapperContext.getTaskId();

        List<Task> tasksToBeScheduled = new LinkedList<>();
        Task forkTask = new Task();
        forkTask.setTaskType(SystemTaskType.FORK.name());
        forkTask.setTaskDefName(SystemTaskType.FORK.name());
        forkTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        forkTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        forkTask.setWorkflowType(workflowInstance.getWorkflowName());
        forkTask.setCorrelationId(workflowInstance.getCorrelationId());
        forkTask.setScheduledTime(System.currentTimeMillis());
        forkTask.setStartTime(System.currentTimeMillis());
        forkTask.setInputData(taskInput);
        forkTask.setTaskId(taskId);
        forkTask.setStatus(Task.Status.COMPLETED);
        forkTask.setWorkflowPriority(workflowInstance.getPriority());
        forkTask.setWorkflowTask(taskToSchedule);

        tasksToBeScheduled.add(forkTask);
        List<List<WorkflowTask>> forkTasks = taskToSchedule.getForkTasks();
        for (List<WorkflowTask> wfts : forkTasks) {
            WorkflowTask wft = wfts.get(0);
            List<Task> tasks2 = taskMapperContext.getDeciderService()
                    .getTasksToBeScheduled(workflowInstance, wft, retryCount);
            tasksToBeScheduled.addAll(tasks2);
        }

        WorkflowTask joinWorkflowTask = workflowInstance
                .getWorkflowDefinition()
                .getNextTask(taskToSchedule.getTaskReferenceName());

        if (joinWorkflowTask == null || !joinWorkflowTask.getType().equals(TaskType.JOIN.name())) {
            throw new TerminateWorkflowException("Fork task definition is not followed by a join task.  Check the blueprint");
        }
        return tasksToBeScheduled;
    }
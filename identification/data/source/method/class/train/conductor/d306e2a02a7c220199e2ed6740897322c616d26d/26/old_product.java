@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflow {

        logger.debug("TaskMapperContext {} in ForkJoinTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Map<String, Object> taskInput = taskMapperContext.getTaskInput();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        int retryCount = taskMapperContext.getRetryCount();

        String taskId = taskMapperContext.getTaskId();
        WorkflowDef workflowDef = taskMapperContext.getWorkflowDefinition();

        List<Task> tasksToBeScheduled = new LinkedList<>();
        Task forkTask = new Task();
        forkTask.setTaskType(SystemTaskType.FORK.name());
        forkTask.setTaskDefName(SystemTaskType.FORK.name());
        forkTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        forkTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        forkTask.setCorrelationId(workflowInstance.getCorrelationId());
        forkTask.setScheduledTime(System.currentTimeMillis());
        forkTask.setEndTime(System.currentTimeMillis());
        forkTask.setInputData(taskInput);
        forkTask.setTaskId(taskId);
        forkTask.setStatus(Task.Status.COMPLETED);
        forkTask.setWorkflowTask(taskToSchedule);

        tasksToBeScheduled.add(forkTask);
        List<List<WorkflowTask>> forkTasks = taskToSchedule.getForkTasks();
        for (List<WorkflowTask> wfts : forkTasks) {
            WorkflowTask wft = wfts.get(0);
            List<Task> tasks2 = taskMapperContext.getDeciderService()
                    .getTasksToBeScheduled(workflowDef, workflowInstance, wft, retryCount);
            tasksToBeScheduled.addAll(tasks2);
        }

        WorkflowTask joinWorkflowTask = workflowDef.getNextTask(taskToSchedule.getTaskReferenceName());
        if (joinWorkflowTask == null || !joinWorkflowTask.getType().equals(WorkflowTask.Type.JOIN.name())) {
            throw new TerminateWorkflow("Dynamic join definition is not followed by a join task.  Check the blueprint");
        }
        return tasksToBeScheduled;
    }
@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) {
        logger.debug("TaskMapperContext {} in DecisionTaskMapper", taskMapperContext);
        List<Task> tasksToBeScheduled = new LinkedList<>();
        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        Map<String, Object> taskInput = taskMapperContext.getTaskInput();
        int retryCount = taskMapperContext.getRetryCount();
        String taskId = taskMapperContext.getTaskId();

        //get the expression to be evaluated
        String caseValue = getEvaluatedCaseValue(taskToSchedule, taskInput);

        //QQ why is the case value and the caseValue passed and caseOutput passes as the same ??
        Task decisionTask = new Task();
        decisionTask.setTaskType(SystemTaskType.DECISION.name());
        decisionTask.setTaskDefName(SystemTaskType.DECISION.name());
        decisionTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
        decisionTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
        decisionTask.setWorkflowType(workflowInstance.getWorkflowName());
        decisionTask.setCorrelationId(workflowInstance.getCorrelationId());
        decisionTask.setScheduledTime(System.currentTimeMillis());
        decisionTask.getInputData().put("case", caseValue);
        decisionTask.getOutputData().put("caseOutput", Collections.singletonList(caseValue));
        decisionTask.setTaskId(taskId);
        decisionTask.setStatus(Task.Status.IN_PROGRESS);
        decisionTask.setWorkflowTask(taskToSchedule);
        tasksToBeScheduled.add(decisionTask);

        //get the list of tasks based on the decision
        List<WorkflowTask> selectedTasks = taskToSchedule.getDecisionCases().get(caseValue);
        //if the tasks returned are empty based on evaluated case value, then get the default case if there is one
        if (selectedTasks == null || selectedTasks.isEmpty()) {
            selectedTasks = taskToSchedule.getDefaultCase();
        }
        //once there are selected tasks that need to proceeded as part of the decision, get the next task to be
        // scheduled by using the decider service
        if (selectedTasks != null && !selectedTasks.isEmpty()) {
            WorkflowTask selectedTask = selectedTasks.get(0);        //Schedule the first task to be executed...
            //TODO break out this recursive call using function composition of what needs to be done and then walk back the condition tree
            List<Task> caseTasks = taskMapperContext.getDeciderService().getTasksToBeScheduled(workflowInstance, selectedTask, retryCount, taskMapperContext.getRetryTaskId());
            tasksToBeScheduled.addAll(caseTasks);
            decisionTask.getInputData().put("hasChildren", "true");
        }
        return tasksToBeScheduled;
    }